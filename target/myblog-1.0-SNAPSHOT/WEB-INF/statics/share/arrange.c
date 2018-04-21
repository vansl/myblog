#include <stdio.h>

int schedule[64][64];	        //赛程表
int amount;		        //队伍数量

void merge(int teams){
     //由四分之一得出其他部分结果
    int half=teams/2;
    int i,j=0;
    for (i =0; i < half; i++){
        for (j=0; j< half; j++){
            schedule[i][j+half]=schedule[i][j]+half;
	    schedule[i+half][j]=schedule[i][j+half];
	    schedule[i+half][j+half]=schedule[i][j];
        }
    }
}

void divide(int teams){
    //规模为1时返回结果
    if (teams==1){
        schedule[0][0]=1;
        return;
    }

    //否则继续缩小规模
    divide(teams/2);
	
    //自下而上合并求解
    merge(teams);
}

int main(){
    printf("plese input the amount of teams: " );
    scanf("%d",&amount);
    
    //队伍数量需小于等于64且是2的幂
    if (amount<1||amount>64||(amount & (amount - 1))!= 0){
        puts("must be smaller than 65 and the power of 2");
        return 1;
    }
    puts("");

    //分治法安排循环赛赛程
    divide(amount);
    
    //打印表头
    int i;
    for (i = 0; i < amount; ++i){
        if (i==0){
          printf(" team\\day  " );
          continue;
        }
        printf("%d     ",i );
        if (i<10){
                printf(" ");    //为了输出美观值小于10时多打印一个空格
          }
    }

    //打印赛程表
    int j;
    for (i =0; i < amount; i++){
      printf("\n\n    ");
        for (j=0; j< amount; j++){
            printf("%d     ", schedule[i][j]);
            if (schedule[i][j]<10){
                printf(" ");    //值小于10时多打印一个空格
            }
      }
    } 
    puts("\n");
    return 0;
}
