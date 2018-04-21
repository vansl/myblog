#!/usr/bin/python3 
#-*- coding:utf-8 -*-

import os
import re
import csv
import requests
import time

game_path=""
game_start=""
game_end=""
time_query_api=""

def config():
    global game_path
    global game_start
    global game_end
    global time_query_api

    filename="th_config.csv"
    if not os.path.exists(filename):
        print("未监测到配置文件，请输入配置信息：")
        while True:
            game_path=input("请输入游戏可执行文件名称（如：LolClient.exe）：")
            game_start=input("请输入限制开始时间，格式hh:mm：")
            game_end=input("请输入限制结束时间，格式hh:mm：")
            time_query_api=input("请打开https://www.nowapi.com/api/life.time获取然后输入时间查询接口：")
            if time_query_api[0:4]!="http":
                time_query_api="http://"+time_query_api
            check_result=check(game_path,game_start,game_end)
            check_api_result=check_api(time_query_api)
            if check_result=="pass" and check_api_result=="pass":
                break
            else:
                if check_result!="pass":
                    print(check_result)
                if check_api_result!="pass":
                    print(check_api_result)
                print("请重新输入配置信息")
                
        with open(filename,'w',newline='') as f:
            writer=csv.writer(f)
            writer.writerow(['game_path','game_start','game_end','time_query_api'])
            writer.writerow([game_path,game_start,game_end,time_query_api])
    with open(filename) as f:
        reader=csv.reader(f)
        next(reader)
        config=next(reader)
        game_path=config[0]
        game_start=config[1]
        game_end=config[2]
        time_query_api=config[3]
        if check_api(time_query_api)!="pass":
            while True:
                check_api_result=check_api(time_query_api)
                if check_api_result!="pass":
                    print("时间查询接口过期或者错误，请重新输入")
                time_query_api=input("请打开www.nowapi.com/api/life.time获取然后输入时间查询接口：")
                if time_query_api[0:4]!="http":
                    time_query_api="http://"+time_query_api
            with open(filename,'wb',newline='') as f: 
                writer=csv.writer(f)
                writer.writerow(['game_path','game_start','game_end','time_query_api'])
                writer.writerow([game_path,game_start,game_end,time_query_api])

def check(game_path,game_start,game_end):
    #if not os.path.exists(game_path):
    #    return "未监测到游戏执行文件"
    if not(re.match(r'^[0-9]{2}:[0-9]{2}$',game_start) and re.match(r'^[0-9]{2}:[0-9]{2}$',game_end)):
        return "时间输入格式错误"
    playtime_set=60*int(game_end[0:2])+int(game_end[3:5])-60*int(game_start[0:2])-int(game_start[3:5])
    if playtime_set<=0:
        return "结束时间必须晚于开始时间"
    #if playtime>120:
    #    return "游戏时间过长"
    return "pass"

def check_api(time_query_api):
    try:
        response=eval(requests.get(time_query_api).text)
        print(response["success"])
        if response["success"]=="0":
            raise Exception()
    except Exception as e:
        return "时间查询接口过期或者错误"
    return "pass"
        
def main():
    print("使用时建议放在单独的桌面上，免于影响且不易快速关闭")
    #try:
    config()
    #except Exception as e:
    #   print(e)
    
    while True:
        response=eval(requests.get(time_query_api).text)
        current_time=response["result"]["datetime_1"][11:16]
        if current_time<game_start or current_time>game_end:
            if os.system("tasklist | findstr \""+game_path+"\"")!="":
                os.system("taskkill /im "+game_path+"\"")

if __name__=='__main__':
    main()

