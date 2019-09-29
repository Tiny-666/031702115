// addressbook.cpp : 此文件包含 "main" 函数。程序执行将在此处开始并结束。
//

#include <string>
#include <iostream>
#include <fstream>
#include <stdlib.h>
#include <algorithm>
#include <stdlib.h>
#include <Windows.h>
using namespace std;
string str;//全局使用gbk编码格式
ofstream Fout("temp.txt");//中间文件，该文件为gbk编码格式
string GbkToUtf8(const char* src_str)
{
	int len = MultiByteToWideChar(CP_ACP, 0, src_str, -1, NULL, 0);
	wchar_t* wstr = new wchar_t[len + 1];
	memset(wstr, 0, len + 1);
	MultiByteToWideChar(CP_ACP, 0, src_str, -1, wstr, len);
	len = WideCharToMultiByte(CP_UTF8, 0, wstr, -1, NULL, 0, NULL, NULL);
	char* str = new char[len + 1];
	memset(str, 0, len + 1);
	WideCharToMultiByte(CP_UTF8, 0, wstr, -1, str, len, NULL, NULL);
	string strTemp = str;
	if (wstr) delete[] wstr;
	if (str) delete[] str;
	return strTemp;
}
string Utf8ToGbk(const char* src_str)
{
	int len = MultiByteToWideChar(CP_UTF8, 0, src_str, -1, NULL, 0);
	wchar_t* wszGBK = new wchar_t[len + 1];
	memset(wszGBK, 0, len * 2 + 2);
	MultiByteToWideChar(CP_UTF8, 0, src_str, -1, wszGBK, len);
	len = WideCharToMultiByte(CP_ACP, 0, wszGBK, -1, NULL, 0, NULL, NULL);
	char* szGBK = new char[len + 1];
	memset(szGBK, 0, len + 1);
	WideCharToMultiByte(CP_ACP, 0, wszGBK, -1, szGBK, len, NULL, NULL);
	string strTemp(szGBK);
	if (wszGBK) delete[] wszGBK;
	if (szGBK) delete[] szGBK;
	return strTemp;
}
void GetName()//获取姓名并输出到中间文件
{
	int i = 0;
	for (i = 0; str[i] != ','; i++);
	int EndPos = i;
	Fout << "\t{" << endl;
	Fout << "\t\t\"level\":" << str[0] << "," << endl;
	Fout << "\t\t\"姓名\": " << "\"" << str.substr(2, EndPos - 2) << "\"," << endl;
	str.erase(2, EndPos - 1);//写入后立刻删除，以便后面的处理（删除了名字以及后面的逗号）
	//Fout << str << endl;
}
void GetPhoneNumber()//获取电话号并输入到中间文件
{
	int i = 0, count = 0, StartPos = 0, EndPos = 0, flag = 0;
	for (i = 2; str[i] != '\0'; i++)
	{
		if (str[i] >= '0' && str[i] <= '9')//找到数字
		{
			if (flag == 0)//未标记起始点
			{
				flag = 1;
				StartPos = i;
			}
			++count;
			if (count > 10)
			{
				EndPos = i;
				break;
			}
		}
		else//非数字
		{
			flag = 0;//清除标记点
			count = 0;
		}
	}
	Fout << "\t\t\"手机\": " << "\"" << str.substr(StartPos, EndPos - StartPos + 1) << "\"," << endl;
	str.erase(StartPos, EndPos - StartPos + 1);//写入后立刻删除，以便后面的处理（删除了其中的电话号）
}
void GetMore()//更多细节提取，输入到中间文件
{
	//路、街、巷名提取—————————————————————————————————————————//
	size_t position1 = 0, position2 = 0, position3 = 0, position4 = 0, position5 = 0;
	position1 = str.find("路");
	position2 = str.find("街");
	position3 = str.find("巷");
	position4 = str.find("村");
	position5 = str.find("道");
	if (position1 != str.npos)
	{
		Fout << "\t\t\t\"" << str.substr(2, position1) << "\"," << endl;
		str.erase(2, position1);
	}
	else if (position2 != str.npos)
	{
		Fout << "\t\t\t\"" << str.substr(2, position2) << "\"," << endl;
		str.erase(2, position2);
	}
	else if (position3 != str.npos)
	{
		Fout << "\t\t\t\"" << str.substr(2, position3) << "\"," << endl;
		str.erase(2, position3);
	}
	else if (position4 != str.npos)
	{
		Fout << "\t\t\t\"" << str.substr(2, position4) << "\"," << endl;
		str.erase(2, position4);
	}
	else if (position5 != str.npos)
	{
		Fout << "\t\t\t\"" << str.substr(2, position5) << "\"," << endl;
		str.erase(2, position5);
	}
	else
		Fout << "\t\t\t\"\"," << endl;
	//路、街、巷名提取完毕———————————————————————————————————————//
	//门牌号提取————————————————————————————————————————————//
	size_t position = 0;
	position = str.find("号");
	if (position != str.npos)
	{
		Fout << "\t\t\t\"" << str.substr(2, position) << "\"," << endl;
		str.erase(2, position);
	}
	else
		Fout << "\t\t\t\"\"," << endl;
	//门牌号提取完毕——————————————————————————————————————————//
}
void GetAddress()//基本提取，过程中的temp字符串需要转换为gbk编码格式
{
	//省份的提取————————————————————————————————————————//
	ifstream prov("Province.txt");//打开省份表,需要将utf-8格式转换为gbk
	Fout << "\t\t\"地址\": " << "[" << endl;
	int Special = 0;
	if (str.substr(2, 4) == "北京" || str.substr(2, 4) == "天津" || str.substr(2, 4) == "上海" || str.substr(2, 4) == "重庆")
	{//四个直辖市
		Special = 1;
		Fout << "\t\t\t\"" << str.substr(2, 4) << "\"," << endl;
	}
	string temp;//临时字符串（存表的每一行，用于比较）
	int Province_Exist = 0;
	while (getline(prov, temp))//提取省
	{
		temp = Utf8ToGbk(temp.c_str());
		if (str.substr(2, 4) == temp.substr(0, 4))
		{
			Province_Exist = 1;
			Fout << "\t\t\t\"" << temp << "\"," << endl;
			int i = 2;
			str.erase(2, 4);//先删除前两个字
			if (str.substr(2, 2) == "省" || str.substr(2, 2) == "市")//判断后面一个字是否是“省”或者“市”
				str.erase(2, 2);//如果是，就删除
			else
			{
				size_t Position = str.find("自治区");
				if (Position != str.npos)
					str.erase(2, Position + 4);
			}
			//Fout << str;
		}
		if (Province_Exist)
			break;
	}
	if (!Province_Exist)
		Fout << "\t\t\t\"\"," << endl;
	prov.close();
	//省提取完毕————————————————————————————————————————//
	//
	//市级的提取————————————————————————————————————————//
	if (!Special)
	{
		ifstream city("city.txt");//打开市级表
		int City_Exist = 0;
		while (getline(city, temp))//提取市
		{
			temp = Utf8ToGbk(temp.c_str());
			if (str.substr(2, 4) == temp.substr(0, 4))
			{
				City_Exist = 1;
				Fout << "\t\t\t\"" << temp << "\"," << endl;
				int EndPos = 0, i = 2;
				for (; str[i] == temp[i - 2]; ++i);
				EndPos = i;//截取
				str.erase(2, EndPos - 2);//写入后立即删除，以便后面处理（市）
				//Fout << str<<endl;
			}
			if (City_Exist)
				break;
		}
		if (!Special && !City_Exist)
			Fout << "\t\t\t\"\"," << endl;
		city.close();
	}
	//市提取完毕————————————————————————————————————————//
	//
	//县级的提取————————————————————————————————————————//
	ifstream county("county.txt");//打开县级表
	int County_Exist = 0;
	while (getline(county, temp))//提取县
	{
		temp = Utf8ToGbk(temp.c_str());
		if (str.substr(2, 4) == temp.substr(0, 4))
		{
			County_Exist = 1;
			Fout << "\t\t\t\"" << temp << "\"," << endl;
			int EndPos = 0, i = 2;
			for (; str[i] == temp[i - 2]; ++i);
			EndPos = i;//截取
			str.erase(2, EndPos - 2);//写入后立即删除，以便后面处理（县）
		}
		if (County_Exist)
			break;
	}
	if (!County_Exist)
		Fout << "\t\t\t\"\"," << endl;
	//Fout << str;
	county.close();
	//县提取完毕————————————————————————————————————————//
	//
	//街道/镇/乡(乡镇级)提取——————————————————————————————————//
	size_t position1 = 0, position2 = 0, position3 = 0, position4 = 0;
	position1 = str.find("镇");
	position2 = str.find("街道");
	position3 = str.find("乡");
	position4 = str.find("合作区");
	if (position1 != str.npos && position1 < 10)
	{
		Fout << "\t\t\t\"" << str.substr(2, position1) << "\"," << endl;
		str.erase(2, position1);
	}
	else if (position2 != str.npos)
	{
		Fout << "\t\t\t\"" << str.substr(2, position2 + 2) << "\"," << endl;
		str.erase(2, position2 + 2);
	}
	else if (position3 != str.npos)
	{
		Fout << "\t\t\t\"" << str.substr(2, position3) << "\"," << endl;
		str.erase(2, position3);
	}
	else if (position4 != str.npos)
	{
		Fout << "\t\t\t\"" << str.substr(2, position4 + 4) << "\"," << endl;
		str.erase(2, position4 + 4);
	}
	else
		Fout << "\t\t\t\"\"," << endl;
	//街道/镇/乡(乡镇级)提取完毕————————————————————————————————//
	//
	//等级判别↓//
	if (str[0] != '1')
		GetMore();
	//判别完毕，删除↓//
	str.erase(0, 2);
	str.erase(str.length() - 1, 1);
	Fout << "\t\t\t\"" << str << "\"" << endl;
	Fout << "\t\t]\n\t}";
}
int main(int argv, char** argc)//argc[1]argc[2]
{
	ifstream in("TEST.txt");//输入文件，该文件为utf-8编码格式
	Fout << '[' << endl;
	while (getline(in, str))//读取一行，并将utf-8编码转换为gbk，此时str为gbk编码
	{
		str = Utf8ToGbk(str.c_str());
		GetName();
		GetPhoneNumber();
		GetAddress();
		if (in.peek() != EOF)
			Fout << ',' << endl;
	}
	Fout << "\n]" << endl;
	in.close();
	Fout.close();
	ifstream FIN("temp.txt");
	ofstream FOUT("output.json");
	while (getline(FIN, str))
	{
		str = GbkToUtf8(str.c_str());
		FOUT << str << endl;
	}
	FIN.close();
	FOUT.close();
	return 0;
}
