#include "stdafx.h"
#include "Monitor.h"


Monitor::Monitor()
{
	buffer = new char[20];
	count = 0;
	nextin = 0;
	nextout = 0;
	NUM = 0;
	/*创建三个信号量，信号名分别是'notempty''notfull'*/
	notempty = CreateSemaphore(NULL, 0, 1,_T("notem"));
	notfull = CreateSemaphore(NULL, 20, 20, _T("notfu"));
	control = CreateSemaphore(NULL, 1, 1, _T("ct"));
}


Monitor::~Monitor()
{
	CloseHandle(notfull);
	CloseHandle(notempty);
	CloseHandle(control);
}
/*往缓冲区写数据，前提是得得到notfull信号量乙己contr信号量，完成写操作之后，给相应的信号量增加相应的值*/
void Monitor::Append(char x)
{
	static int p_count = 1;
	int p = p_count++;
	while (1)
	{
		P(notfull);
		P(control);
		buffer[nextin] = x;
		nextin = (nextin + 1) % bufferlength;
		count++;
		Wait(p, 1, 3, "生产者开始生产产品");
		Wait(p, 1, 3, "生产者生产完产品");
		V(control);
		V(notempty);
	}
}
/*从缓冲区中读数据，前提是获得notempty以及control信号量，完成读操作之后，要对control信号量和notfull信号量做相应的修改*/
void Monitor::Take()
{
	static int c_count = 1;
	int c = count++;
	while (1)
	{
		P(notempty);
		P(control);
		char x;
		x = buffer[nextout];
		nextout = (nextout + 1) % bufferlength;
		count--;
		Wait(c, 1, 2, "——————消费者开始消费产品");
		Wait(c, 1, 2, "——————消费者消费完产品");
		V(control);
		V(notempty);
	}
}
void Monitor::Wait(int NUM, int min, int max, LPCSTR info)
{
	char s_out[128];
	const int BASETIME = 500;
	int wait_time = 0;
		if (max == min)
			wait_time = min * BASETIME;
		else
			wait_time = rand() % (max*BASETIME - min * BASETIME) + min * BASETIME;
	sprintf_s(s_out, "No%d%s\n", NUM, info);
	printf(s_out);
	Sleep(wait_time);
}
