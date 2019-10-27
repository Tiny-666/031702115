package GAME;

//import java.util.Arrays;

public class CHUPAI {
	static int[] fangkuai = new int[15];
	static int[] meihua = new int[15];
	static int[] hongtao = new int[15];
	static int[] heitao = new int[15];
	static int[] shoupai = new int[13];
	public static void Trans(String str)//统计手牌
	{
		String AllCard = str.replace(" ", "");//去除全部空格
		//AllCard = str.replace("\"", "");//去除全部引号
		AllCard = AllCard.replace("10", ":");//将10替换成:
		AllCard = AllCard.replace("J", ";");//将J替换成;
		AllCard = AllCard.replace("Q", "<");//将Q替换成<
		AllCard = AllCard.replace("K", "=");//将K替换成=
		AllCard = AllCard.replace("A", ">");//将A替换成>
		for(int i = 0; i < AllCard.length(); i += 2)//统计手牌
		{
			String Tmp = AllCard.substring(i, i+2);
			//Tmp.charAt(0)是花色
			//Tmp.charAt(1)是数字
			if(Tmp.charAt(0) == '#')
			{
				fangkuai[Tmp.charAt(1)-50]+=1;
				fangkuai[13]+=1;
			}
			else if(Tmp.charAt(0) == '*')
			{
				meihua[Tmp.charAt(1)-50]+=1;
				meihua[13]+=1;
			}
			else if(Tmp.charAt(0) == '&')
			{
				hongtao[Tmp.charAt(1)-50]+=1;
				hongtao[13]+=1;
			}
			else
			{
				heitao[Tmp.charAt(1)-50]+=1;
				heitao[13]+=1;
			}
			shoupai[Tmp.charAt(1)-50]+=1;
		}
		fangkuai[14]=1;
		meihua[14]=2;
		hongtao[14]=3;
		heitao[14]=4;
	}
	public static String GetACard(int i)//取出一张牌及其对应花色，末尾不带空格
	{
		String str="";
		if(fangkuai[i]==1)
		{
				fangkuai[i]=0;
				fangkuai[13]-=1;
				shoupai[i]-=1;
				str+="#"+(char)(i+50);
		}
		else if(meihua[i]==1)
		{
				meihua[i]=0;
				meihua[13]-=1;
				shoupai[i]-=1;
				str+="*"+(char)(i+50);
		}
		else if(hongtao[i]==1)
		{
				hongtao[i]=0;
				hongtao[13]-=1;
				shoupai[i]-=1;
				str+="&"+(char)(i+50);
		}
		else if(heitao[i]==1)
		{
				heitao[i]=0;
				heitao[13]-=1;
				shoupai[i]-=1;
				str+="$"+(char)(i+50);
		}
		return str;
	}
	public static String GetAPair(int j)//取出一对，末尾不带空格
	{
		String Bottom="";
		int count=0;
		if(fangkuai[j]==1)
		{
				fangkuai[j]=0;
				fangkuai[13]-=1;
				Bottom+="#"+(char)(j+50)+" ";
				count++;
		}
		if(meihua[j]==1)
		{
				meihua[j]=0;
				meihua[13]-=1;
				Bottom+="*"+(char)(j+50);
				if(count<1)
					Bottom+=" ";
				else
				{
					shoupai[j]-=2;
					return Bottom;
				}
				count++;
		}
		if(hongtao[j]==1)
		{
				hongtao[j]=0;
				hongtao[13]-=1;
				Bottom+="&"+(char)(j+50);
				if(count<1)
					Bottom+=" ";
				else
				{
					shoupai[j]-=2;
					return Bottom;
				}
				count++;
		}
		if(heitao[j]==1)
		{
				heitao[j]=0;
				heitao[13]-=1;
				Bottom+="$"+(char)(j+50);
				if(count<1)
					Bottom+=" ";
				else
				{
					shoupai[j]-=2;
					return Bottom;
				}
				count++;				
		}
		return "NULL";
	}
	public static String GetTwoPairs(int p1,int p2)//取两对，p2是较小者
	{
		String Bottom="\"";
		Bottom+=GetAPair(p2)+" ";
		Bottom+=GetAPair(p1);
		int i;
		for(i=0;i<=12;i++)//找到两对后，找1张单牌
		{
			if(shoupai[i]==1)
			{
				if(p1<i)//较大的对子牌面比单牌牌面小，单牌加后面
				Bottom+=" "+GetACard(i)+"\"";
				else if(p2>i)//较小的对子牌面比单牌牌面大，单牌加前面
				{
					StringBuffer Str=new StringBuffer(Bottom);
					Str.insert(1,GetACard(i)+" " );
					Bottom=Str.toString()+"\"";
				}
				else
				{
					StringBuffer Str=new StringBuffer(Bottom);
					Str.insert(7,GetACard(i)+" " );
					Bottom=Str.toString()+"\"";
				}
				break;
			}
		}
		if(i==13)//没找到单牌
		{
			for(i=0;i<=12;i++)//拆对子
			{
				if(shoupai[i]>0)
				{
					if(p1<i)//较大的对子牌面比单牌牌面小，单牌加后面
					Bottom+=" "+GetACard(i)+"\"";
					else if(p2>i)//较小的对子牌面比单牌牌面大，单牌加前面
					{
						StringBuffer Str=new StringBuffer(Bottom);
						Str.insert(1,GetACard(i)+" " );
						Bottom=Str.toString()+"\"";
					}
					else
					{
						StringBuffer Str=new StringBuffer(Bottom);
						Str.insert(7,GetACard(i)+" " );
						Bottom=Str.toString()+"\"";
					}
					break;
				}
			}
		}
		return Bottom;
	}
	public static String Get_Gourd_Pair(int tri,int j)//取出葫芦的一对，末尾不带空格
	{
		String Bottom="";
		int count=0,fk=0,mh=0,ht=0,heit=0,max=-1,max2=-1,str_exist=0,str_destroy=-1,th_exist=0;
		if(shoupai[j]==2)
		{
			for(int i=0;i<13;i++)//判断原本有没有顺子
			{
				if(shoupai[i]>0)
				{
					count++;
					if(max2<count)
						max2=count;
				}
				else
					count=0;
			}
			if(max2>=5)//原本有顺子
				str_exist=1;
			count=0;
			for(int i=0;i<13;i++)//判断有没有其他顺子
			{
				if(shoupai[i]>0)
				{
					if(i!=tri&&i!=j)
					{
						count++;
						if(max<count)
							max=count;
					}
					else
						count=0;
				}
				else
					count=0;
			}
			if(max>=5)//有其他顺子，可以拿出来
				str_destroy=0;
			else
				str_destroy=1;
		}
		//判断是否存在其他同花
		count=0;
		if((fangkuai[tri]==0&&fangkuai[13]>4)||(fangkuai[tri]==1&&fangkuai[13]>5))
			th_exist=1;
		else if((meihua[tri]==0&&meihua[13]>4)||(meihua[tri]==1&&meihua[13]>5))
			th_exist=1;
		else if((hongtao[tri]==0&&hongtao[13]>4)||(hongtao[tri]==1&&hongtao[13]>5))
			th_exist=1;
		else if((heitao[tri]==0&&heitao[13]>4)||(heitao[tri]==1&&heitao[13]>5))
			th_exist=1;
		if(th_exist==1)//存在其他同花
		{
			if(fangkuai[j]==1)
			{
				if((fangkuai[tri]==0&&fangkuai[13]!=5)||(fangkuai[tri]==1&&fangkuai[13]!=6))
				{
					fangkuai[j]=0;
					fangkuai[13]-=1;
					Bottom+="#"+(char)(j+50)+" ";
					fk=1;
					count++;
				}
			}
			if(meihua[j]==1)
			{
				if((meihua[tri]==0&&meihua[13]!=5)||(meihua[tri]==1&&meihua[13]!=6))
				{
					meihua[j]=0;
					meihua[13]-=1;
					Bottom+="*"+(char)(j+50);
					mh=1;
					if(count<1)
						Bottom+=" ";
					else
					{
						shoupai[j]-=2;
						return Bottom;
					}
					count++;
				}
			}
			if(hongtao[j]==1)
			{
				if((hongtao[tri]==0&&hongtao[13]!=5)||(hongtao[tri]==1&&hongtao[13]!=6))
				{
					hongtao[j]=0;
					hongtao[13]-=1;
					Bottom+="&"+(char)(j+50);
					ht=1;
					if(count<1)
						Bottom+=" ";
					else
					{
						shoupai[j]-=2;
						return Bottom;
					}
					count++;
				}
			}
			if(heitao[j]==1)
			{
				if((heitao[tri]==0&&heitao[13]!=5)||(heitao[tri]==1&&heitao[13]!=6))
				{
					heitao[j]=0;
					heitao[13]-=1;
					Bottom+="$"+(char)(j+50);
					heit=1;
					if(count<1)
						Bottom+=" ";
					else
					{
						shoupai[j]-=2;
						return Bottom;
					}
					count++;
				}
			}
			//没破坏同花时，会在上面结束
			if(count==1)//只找到单张，需要还原回去(破坏了同花) 	  //1.shoupai[j]=2,单纯的对子,其中一张破坏了同花
																  //2.shoupai[j]=3,破坏了两个同花（不可能）
			{
				if(fk==1)
				{
					fangkuai[j]=1;
					fangkuai[13]+=1;
				}
				else if(mh==1)//只需要还原一张，故可以加上else来加快速度
				{
					meihua[j]=1;
					meihua[13]+=1;
				}
				else if(ht==1)
				{
					hongtao[j]=1;
					hongtao[13]+=1;
				}
				else if(heit==1)
				{
					heitao[j]=1;
					heitao[13]+=1;
				}
			}
		}
		//没有其他同花，或者取出时同花被破坏
			if((th_exist==0&&str_exist==0)||str_destroy==0)//顺子没被破坏，或者不存在顺子和同花
				return GetAPair(j);
		return "NULL";
	}
	public static String GetATri(int i)//取出三条，末尾不带空格
	{
		String Bottom="";
		int count=0;
		if(fangkuai[i]==1)
		{
			fangkuai[i]=0;
			fangkuai[13]-=1;
			Bottom+="#"+(char)(i+50)+" ";
			count++;
		}
		if(meihua[i]==1)
		{
			meihua[i]=0;
			meihua[13]-=1;
			Bottom+="*"+(char)(i+50)+" ";
			count++;
		}
		if(hongtao[i]==1)
		{
			hongtao[i]=0;
			hongtao[13]-=1;
			Bottom+="&"+(char)(i+50);
			count++;
			if(count<3)
				Bottom+=" ";
		}
		if(heitao[i]==1)
		{
			heitao[i]=0;
			heitao[13]-=1;
			Bottom+="$"+(char)(i+50);
		}
		shoupai[i]-=3;//扣除相应牌
		return Bottom;
	}
	public static int Link_Double()//判断连对，返回较小对子的下标，没有则返回-1
	{
		for(int i=12;i>=1;i--)
		{
			if(shoupai[i]==2&&shoupai[i-1]==2)
				return i-1;
		}
		return -1;
	}
	public static int Trible()//判断是否有三张相同。若有，返回最大的下标。没有返回-1
	{
		int i;
		for(i=12;i>=0;i--)
		{
			if(shoupai[i]==3)
			break;
		}
		return i;
	}
	public static String THS(int[] arr,String huase)//单个花色同花顺的判断，有则取出最大的，没有返回“NULL”
	{
		String str="";
		for(int i=8;i>=0;i--)
		{
			if(arr[i]>0&&arr[i+1]>0&&arr[i+2]>0&&arr[i+3]>0&&arr[i+4]>0)
			{
				str+="\""+huase+(char)(i+50)+" "+huase+(char)(i+51)+" "+huase+
						(char)(i+52)+" "+huase+(char)(i+53)+" "+huase+(char)(i+54)+"\"";
				arr[i]-=1;
				arr[i+1]-=1;
				arr[i+2]-=1;
				arr[i+3]-=1;
				arr[i+4]-=1;
				arr[13]-=5;
				shoupai[i]-=1;
				shoupai[i+1]-=1;
				shoupai[i+2]-=1;
				shoupai[i+3]-=1;
				shoupai[i+4]-=1;
				return str;
			}
		}
		return "NULL";
	}
	public static String TH(int[] arr,String huase,String dun)//取出单花色中最大的同花，并返回
	{
		StringBuffer str=new StringBuffer();
		str.insert(0, "\"");
		int count=0,CouNT=arr[13],divide_pair=0,c1=-1,c2=-1,c3=-1,c4=-1,c5=-1;
		for(int i=12;i>=0;i--)
		{
			if(arr[i]>0)
			{
				if(CouNT-5>0&&shoupai[i]>1)//绕过对子,从大的对子开始绕
					CouNT--;
				else
				{
					if(shoupai[i]==2)
					{
						divide_pair++;
						if(divide_pair==2)//拆了两个对子
						{
							if(fangkuai[14]!=arr[14]&&fangkuai[13]>4);//有其他的同花
							else if(meihua[14]!=arr[14]&&meihua[13]>4);
							else if(hongtao[14]!=arr[14]&&hongtao[13]>4);
							else if(heitao[14]!=arr[14]&&heitao[13]>4);
							if(dun=="Bottom")//底墩
							{
								if(fangkuai[14]==arr[14]&&fangkuai[13]>9);//自身有10张同花及以上
								else if(meihua[14]==arr[14]&&meihua[13]>9);
								else if(hongtao[14]==arr[14]&&hongtao[13]>9);
								else if(heitao[14]==arr[14]&&heitao[13]>9);
								else//都没有 
									break;
							}
							else//中墩
							{
								if(fangkuai[14]==arr[14]&&fangkuai[13]>4);//自身有5张同花及以上
								else if(meihua[14]==arr[14]&&meihua[13]>4);
								else if(hongtao[14]==arr[14]&&hongtao[13]>4);
								else if(heitao[14]==arr[14]&&heitao[13]>4);
								else//都么有
									break;
							}
						}
					}
					str.insert(0, huase+(char)(i+50));
					count++;
					if(count==1) c1=i;
					else if(count==2) c2=i;
					else if(count==3) c3=i;
					else if(count==4) c4=i;
					else if(count==5) c5=i;
					if(count<5)
						str.insert(0, " ");
					else
					{
						arr[c1]=arr[c2]=arr[c3]=arr[c4]=arr[c5]=0;
						arr[13]-=5;
						shoupai[c1]-=1;
						shoupai[c2]-=1;
						shoupai[c3]-=1;
						shoupai[c4]-=1;
						shoupai[c5]-=1;
						str.insert(0, "\"");
						String Str=str.toString();
						return Str;
					}
				}
			}
		}
		return "NULL";
	}
	public static String Judge_THS()//判断整个手牌中有没有同花顺,有则取出，没有返回“NULL”
	{
		String Bottom;
		if((Bottom=THS(fangkuai,"#"))=="NULL")
		{
			Bottom="";
			if((Bottom=THS(meihua,"*"))=="NULL")
			{
				Bottom="";
				if((Bottom=THS(hongtao,"&"))=="NULL")
				{
					Bottom="";
					Bottom=THS(heitao,"$");
				}
			}
		}
		return Bottom;
	}
	public static String Judge_Bomb()//判断整个手牌中有没有炸弹（四张相同，但没匹配剩下的单牌），末尾不带空格，有则取出，没有返回“NULL”
	{
		String Bottom="";
		int i;
		for(i=12;i>=0;i--)//从大牌开始找
		{
			if(shoupai[i]==4)//找到四张相同牌,只找一次
			{
				Bottom+="\"#"+(char)(i+50)+" *"+(char)(i+50)+" &"+
						(char)(i+50)+" $"+(char)(i+50);
				fangkuai[i]=meihua[i]=hongtao[i]=heitao[i]=0;
				fangkuai[13]-=1;
				meihua[13]-=1;
				hongtao[13]-=1;
				heitao[13]-=1;
				shoupai[i]-=4;//扣除相应牌
				return Bottom;
			}
		}
		return "NULL";
	}
	public static String Judge_Gourd(String dun)//判断整个手牌中有没有葫芦，有则取出，没有返回“NULL”
	{
		String Bottom="",Pair="";
		int trible=Trible();
		int link=Link_Double(),pair=0,tri=0;
		if(trible!=-1)//有三张相同
		{
			if(dun=="Bottom")//是底墩
			{
				for(int i=0;i<=12;i++)
				{
					if(shoupai[i]==2)//判断还剩下多少对
						pair++;
					else if(shoupai[i]==3&&i!=trible)//判断除了自己以外还有没有三条
						tri++;
				}
				if(tri<1&&pair<3)
				{//判断有没有其他顺子、同花
					if(fangkuai[trible]>0&&fangkuai[13]>5);
					else if(meihua[trible]>0&&meihua[13]>5);
					else if(hongtao[trible]>0&&hongtao[13]>5);
					else if(heitao[trible]>0&&heitao[13]>5);
					else if(meihua[trible]==0&&meihua[13]>5);
					else if(hongtao[trible]==0&&hongtao[13]>5);
					else if(heitao[trible]==0&&heitao[13]>5);
					else if(fangkuai[trible]==0&&fangkuai[13]>5);
					else//没有其他同花,找其他顺子
					{
						int i;
						for(i=0;i<8;i++)
						{
							if(shoupai[i]>0&&shoupai[i+1]>0&&shoupai[i+2]>0&&shoupai[i+3]>0&&shoupai[i+4]>0)
							{
								if(i<=trible&&i+4>=trible)
									continue;
								else//找到其他顺子
									break;
							}
						}
						if(i==9)//没找到
							return "NULL";
					}
				}
			}
			else//是中墩
			{
				int p=0;
				for(int i=0;i<=12;i++)
				{
					if(shoupai[i]==2)//找最大对子
						p=i;
					else if(shoupai[i]==3&&i!=trible)//判断除了自己以外还有没有三条
						tri++;
				}
				if(p>7||tri>0)
					return "NULL";
			}
			if(link==-1)//没有连对
			{
				for(int j=0;j<=12;j++)//直接找剩下的最小的对子，若没能返回，说明没有能凑成葫芦的牌
				{
					if(shoupai[j]==2)//找到可以凑成葫芦的牌
					{
						Pair=Get_Gourd_Pair(trible,j);
						if(Pair!="NULL")
						{
							if(j>trible)//对子牌面比三条牌面大
								Bottom+="\""+GetATri(trible)+" "+Pair+"\"";
								else//对子牌面比三条牌面小
								Bottom+="\""+Pair+" "+GetATri(trible)+"\"";
								return Bottom;
						}
					}
				}
				if(dun!="Middle")//不是中墩
				{
					for(int j=0;j<=12;j++)//直接找剩下的最小的对子，若没能返回，说明没有能凑成葫芦的牌
					{
						if(shoupai[j]>2&&j!=trible)//找到可以凑成葫芦的牌
						{
							Pair=Get_Gourd_Pair(trible,j);
							if(Pair!="NULL")
							{
								if(j>trible)//对子牌面比三条牌面大
									Bottom+="\""+GetATri(trible)+" "+Pair+"\"";
									else//对子牌面比三条牌面小
									Bottom+="\""+Pair+" "+GetATri(trible)+"\"";
									return Bottom;
							}
						}
					}
				}
			}
			else//有连对
			{
				for(int j=0;j<=12;j++)//开始找剩下的最小的对子,若没能返回，说明只有连对，没有其他对子
				{
					if(j==link)//遇到连对，跳过
					{
						j+=1;
						continue;
					}
					else if(shoupai[j]==2)//一个对子，不属于连对中的一对，取出
					{
						Pair=Get_Gourd_Pair(trible,j);
						//System.out.println(Pair);
						if(Pair!="NULL")
						{
							if(j>trible)//对子牌面比三条牌面大
								Bottom+="\""+GetATri(trible)+" "+Pair+"\"";
								else//对子牌面比三条牌面小
								Bottom+="\""+Pair+" "+GetATri(trible)+"\"";
								//System.out.println(Arrays.toString(shoupai));
								return Bottom;
						}
					}
				}
				//只有连对，没有其他对子，拆连对
				Pair=Get_Gourd_Pair(trible,link);
				if(Pair!="NULL")
				{
					if(link>trible)//对子牌面比三条牌面大
						Bottom+="\""+GetATri(trible)+" "+Pair+"\"";
						else//对子牌面比三条牌面小
						Bottom+="\""+Pair+" "+GetATri(trible)+"\"";
					return Bottom;
				}
				else
				{
					Pair=Get_Gourd_Pair(trible,link+1);
					if(Pair!="NULL")
					{
						if(link>trible)//对子牌面比三条牌面大
							Bottom+="\""+GetATri(trible)+" "+Pair+"\"";
							else//对子牌面比三条牌面小
							Bottom+="\""+Pair+" "+GetATri(trible)+"\"";
						return Bottom;
					}
				}
			}
		}
		return "NULL";
	}
	public static String Judge_TH(String dun)//判断整个手牌中有没有同花，有则取出，没有返回“NULL”
	{
		String Bottom="NULL";
		int i;
		for(i=12;i>=0;i--)
		{
			if(shoupai[i]>0)
			{
				if(fangkuai[13]>4&&fangkuai[i]>0)
				{
					Bottom=TH(fangkuai,"#",dun);
					if(Bottom!="NULL")
					break;
				}
				if(meihua[13]>4&&meihua[i]>0)
				{
					Bottom=TH(meihua,"*",dun);
					if(Bottom!="NULL")
					break;
				}
				if(hongtao[13]>4&&hongtao[i]>0)
				{
					Bottom=TH(hongtao,"&",dun);
					if(Bottom!="NULL")
					break;
				}
				if(heitao[13]>4&&heitao[i]>0)
				{
					Bottom=TH(heitao,"$",dun);
					if(Bottom!="NULL")
					break;
				}
			}
		}
		return Bottom;
	}
	public static String Judge_Str(String dun)//判断整个手牌中有没有顺子，有则取出，没有返回“NULL”
	{
		String Bottom="\"";
		int CouNT=0,max=-1,pos=-1;
		for(int i=0;i<13;i++)
		{
			if(shoupai[i]>0)//查找最长连续串
			{
				CouNT++;
				if(CouNT>max)
				{
					max=CouNT;
					pos=i;
				}
			}
			else
				CouNT=0;
		}
		if(max<5)//最大长度<5，没有顺子
			return "NULL";
		for(int i=8;i>=0;i--)
		{
			if(shoupai[i]>0&&shoupai[i+1]>0&&shoupai[i+2]>0&&shoupai[i+3]>0&&shoupai[i+4]>0)
			{
				if(i<=pos)
				{
					if(max>5&&(shoupai[i]==2||shoupai[i+1]==2||shoupai[i+2]==2||shoupai[i+3]==2||shoupai[i+4]==2))
					{//能跳的大对子尽量跳
						if(i>0)
							if(shoupai[i-1]==1)//避免破坏前面的对子
							{
								max--;
								continue;
							}
					}
				}
				if(dun=="Middle")//是中墩
				{
					if(max>5&&(shoupai[i]==1||shoupai[i+1]==1||shoupai[i+2]==1||shoupai[i+3]==1||shoupai[i+4]==1))
					{//能跳的单牌尽量跳
						if(i>0)
							if(shoupai[i-1]==1)//避免破坏前面的对子
							{
								max--;
								continue;
							}
					}
				}
				Bottom+=GetACard(i)+" ";
				Bottom+=GetACard(i+1)+" ";
				Bottom+=GetACard(i+2)+" ";
				Bottom+=GetACard(i+3)+" ";
				Bottom+=GetACard(i+4)+"\"ST";
				return Bottom;
			}
		}
		return "NULL";
	}
	public static String Judge_Tri()//判断整个手牌中有没有三条，有则取出，没有返回“NULL”
	{
		String Bottom="\"";
		int count=0;
		int trible=Trible();
		if(trible==-1)
			return "NULL";
		//System.out.println(trible);
		int flag=0;
		for(int i=0;i<=12;i++)//之后找2张单牌
		{
			if(shoupai[i]==1)
			{
				if(i>trible)
				{
					if(flag==0)//没加三条
					{
						if(count==1)
						{
							Bottom+=GetATri(trible)+" "+GetACard(i);
							count++;
						}
						else if(count<1)
						{
							Bottom+=GetATri(trible)+" "+GetACard(i);
							count++;
						}
						flag=1;
					}
					else//加了三条
					{
						Bottom+=" "+GetACard(i);
						count++;
					}
				}
				else
				{
					Bottom+=GetACard(i)+" ";
					count++;
				}
			}
			if(count==2)
			{
				if(flag==0)
					Bottom+=GetATri(trible)+"\"";
					else
						Bottom+="\"";
				break;
			}
		}
		return Bottom;
	}
	public static String Judge_TwoP(String dun)//判断整个手牌中有没有两对（包括连对），有则取出，没有返回“NULL”
	{
		String Bottom="";
		int link=Link_Double();
		int p1=-1,p2=-1,p3=-1,p4=-1,p5=-1,p6=-1,count=0;
		for(int i=12;i>=0;i--)//找对子，从大的开始
		{
			if(shoupai[i]==2)
			{
				count++;
				if(count==1)
					p1=i;
				else if(count==2)
					p2=i;
				else if(count==3)
					p3=i;
				else if(count==4)
					p4=i;
				else if(count==5)
					p5=i;
				else if(count==6)
					p6=i;
			}
		}
		if(count<2)//没找到两对
			return "NULL";
		if(dun=="Bottom")//是底墩
		{
			if(p4==-1)//只有两对或三对，要分到前一墩一对
				Bottom=Judge_Pair();
			else//有四对或五对以及六对（此时是特殊牌型）
			{
				if(link!=-1)//存在连对
					Bottom=GetTwoPairs(link+1,link);
				else//不存在连对
				{
					if(p5==-1)//四对
						Bottom=GetTwoPairs(p1,p4);
					else if(p6==-1)//五对
						Bottom=GetTwoPairs(p1,p5);
					else//六对
						Bottom=GetTwoPairs(p1,p6);
				}
			}
		}
		else//是中墩
		{
			if(p3==-1)//只有两对，要分到前墩一对
				Bottom=Judge_Pair();
			else//有三对或四对
			{
				if(link!=-1)//存在连对
					Bottom=GetTwoPairs(link+1,link);
				else//不存在连对
				{
					if(p4==-1)//三对
					Bottom=GetTwoPairs(p2,p3);
					else//四对
					Bottom=GetTwoPairs(p3,p4);
				}
			}
		}
		return Bottom;
	}
	public static String Judge_Pair()//判断整个手牌中有没有对子，有则取出，没有返回“NULL”
	{
		String Bottom="\"";
		int j;
		int p=-1,count=0;
		for(j=12;j>=0;j--)//找对子，从最大的开始
		{
			if(shoupai[j]==2)//找到一对，count+1
			{
				p=j;
				break;
			}
		}
		if(p==-1)//没找到对子
			return "NULL";
		else//找到对子
		{
			int flag=0;
			for(int i=0;i<=12;i++)//之后找3张单牌
			{
				if(shoupai[i]==1)
				{
					if(i>p)
					{
						if(flag==0)//没加对子
						{
							if(count==2)
							{
								Bottom+=GetAPair(p)+" "+GetACard(i);
								count++;
							}
							else if(count<2)
							{
								Bottom+=GetAPair(p)+" "+GetACard(i);
								count++;
							}
							flag=1;
						}
						else//加了对子
						{
							Bottom+=" "+GetACard(i);
							count++;
						}
					}
					else
					{
						Bottom+=GetACard(i)+" ";
						count++;
					}
				}
				if(count==3)
				{
					if(flag==0)
						Bottom+=GetAPair(j)+"\"";
						else
							Bottom+="\"";
					break;
				}
			}
		}
		return Bottom;
	}
	public static String Judge_WuLong()//以上均没有，拿出从小到大的散牌,大的优先
	{
		StringBuffer str=new StringBuffer();
		str.insert(0, "\"");
		int count=0;
		for(int i=12;i>=0;i--)
		{
			if(shoupai[i]==1)
			{
				str.insert(0, GetACard(i));
				count++;
				if(count<5)
					str.insert(0, " ");
				else
					break;
			}
		}
		str.insert(0, "\"");
		String Str=str.toString();
		return Str;
	}
	public static String Get_Max(String dun)
	{
		String str;
		//判断同花顺
		if((str=Judge_THS())!="NULL")
		{
			//System.out.println(Arrays.toString(shoupai));
			return str;
		}
		//判断炸弹
		if((str=Judge_Bomb())!="NULL")
		{
			//System.out.println(Arrays.toString(shoupai));
			return str;
		}
		//判断葫芦
		if((str=Judge_Gourd(dun))!="NULL")
		{
			//System.out.println(Arrays.toString(shoupai));
			return str;
		}
			
		//判断同花
		if((str=Judge_TH(dun))!="NULL")
		{
			//System.out.println(Arrays.toString(shoupai));
			str+="TH";
			return str;
		}
		//判断顺子
		if((str=Judge_Str(dun))!="NULL")
		{
			//System.out.println(Arrays.toString(shoupai));
			return str;
		}
		//判断三条
		if((str=Judge_Tri())!="NULL")
		{
			//System.out.println(Arrays.toString(shoupai));
			return str;
		}
		//判断两对
		if((str=Judge_TwoP(dun))!="NULL")
		{
			//System.out.println(Arrays.toString(shoupai));
			return str;
		}
		//判断对子
		if((str=Judge_Pair())!="NULL")
		{
			//System.out.println(Arrays.toString(shoupai));
			return str;
		}
		//以上均没有，拿出散牌
		if((str=Judge_WuLong())!="NULL")
		{
			//System.out.println(Arrays.toString(shoupai));
			return str;
		}
		return "NULL";
	}
	public String CardPlaying(String str) 
	{
		//String str="*4 #4 $3 *8 *9 *3 &K #8 *J &4 #A #10 *Q";
		System.out.println(str);
		String Result ="";
		String Top ="";
		String Middle ="";
		String Bottom ="";
		Trans(str);//统计手牌
		//获取底墩
		Bottom=Get_Max("Bottom");
		//获取中墩
		Middle=Get_Max("Middle");
		//剩下的牌拿出来放到顶墩以及对炸弹的补充
		int tri=Trible();
		int DOUB=-1;
		Top+="\"";
		if(tri!=-1)//有三张相同
			Top+=GetATri(tri)+"\"";
		else//没有三张相同
		{
			for(int i=12;i>=0;i--)
			{//找对子
				if(shoupai[i]==2)
				{//找到一个对子后退出循环,找不到也无所谓
					Top+=GetAPair(i);
					DOUB=i;
					break;
				}
			}
		}
		int count=0;
		for(int i=0;i<13;i++)//此时可能剩下一对或者3张全单牌或5张全单牌
		{
			if(shoupai[i]>0)
			{
				int CNT=shoupai[i];
				for(int j=0;j<CNT;j+=1)
				{
					if(Bottom.length()==12)//底墩是炸弹，且还未补全
					{
						if((char)(i+50)>Bottom.charAt(2))//要加的单牌牌面大于炸弹，直接加在后面
						Bottom+=" "+GetACard(i)+"\"";
						else//要加的单牌牌面小于炸弹，加在前面
						{
							StringBuffer Str=new StringBuffer(Bottom);
							Str.insert(1,GetACard(i)+" " );
							Bottom=Str.toString();
							Bottom+="\"";
						}
					}
					else if(Middle.length()==12)//中墩是炸弹，且还未补全
					{
						if((char)(i+50)>Middle.charAt(2))//要加的单牌牌面大于炸弹，直接加在后面
						Middle+=" "+GetACard(i)+"\"";
						else//要加的单牌牌面小于炸弹，加在前面
						{
							StringBuffer Str=new StringBuffer(Middle);
							Str.insert(1,GetACard(i)+" " );
							Middle=Str.toString();
							Middle+="\"";
						}
					}
					else if(DOUB!=-1)//中墩底墩俱全，有对子
					{
						if(i>DOUB)
						Top+=" "+GetACard(i)+"\"";
						else
						{
							StringBuffer Str=new StringBuffer(Top);
							Str.insert(1,GetACard(i)+" " );
							Top=Str.toString();
							Top+="\"";
							break;
						}
					}
					else//中墩底墩俱全，无对子
					{
						Top+=GetACard(i);
						count++;
						if(count<3)//顶墩不存在对子
							Top+=" ";
						else
						{
							Top+="\"";
							break;
						}
					}
				}
			}
		}
		if(Bottom.length()>16&&Middle.length()>16)//两墩出现顺子+顺子/同花+同花/同花+顺子的情况,可能会反水
		{
			int[] a=new int[5];
			int[] b=new int[5];
			int cnt=0;
			if(Bottom.charAt(16)<Middle.charAt(16))//根据标志字符串判断,字典序T在S后面，故TH>ST
			{
				String temp=Middle;
				Middle=Bottom;
				Bottom=temp;
			}
			else
			{
				Bottom=Bottom.replaceAll("TH", "");
				Bottom=Bottom.replaceAll("ST", "");
				Middle=Middle.replaceAll("TH", "");
				Middle=Middle.replaceAll("ST", "");
				Bottom=Bottom.replaceAll("\"", "");
				Middle=Middle.replaceAll("\"", "");
				Trans(Bottom);
				for(int i=12;i>=0;i--)
					if(shoupai[i]>0)
					{
						a[cnt++]=i;
						shoupai[i]=0;
						if(cnt==5)
							break;
					}
				Trans(Middle);
				for(int i=12;i>=0;i--)
					if(shoupai[i]>0)
					{
						b[cnt++]=i;
						if(cnt==5)
							break;
					}
				//数组a存Bottom的牌面值，b存Middle的牌面值
				for(int i=0;i<5;i++)
					if(b[i]>a[i])
					{
						String temp=Middle;
						Middle=Bottom;
						Bottom=temp;
						break;
					}
					else if(a[i]>b[i])
						break;
			}
		}
		Top=Top.replaceAll(":", "10");
		Top=Top.replaceAll(";", "J");
		Top=Top.replaceAll("<", "Q");
		Top=Top.replaceAll("=", "K");
		Top=Top.replaceAll(">", "A");
		Bottom=Bottom.replaceAll("TH", "");
		Bottom=Bottom.replaceAll("ST", "");
		Bottom=Bottom.replaceAll(":", "10");
		Bottom=Bottom.replaceAll(";", "J");
		Bottom=Bottom.replaceAll("<", "Q");
		Bottom=Bottom.replaceAll("=", "K");
		Bottom=Bottom.replaceAll(">", "A");
		Middle=Middle.replaceAll("TH", "");
		Middle=Middle.replaceAll("ST", "");
		Middle=Middle.replaceAll(":", "10");
		Middle=Middle.replaceAll(";", "J");
		Middle=Middle.replaceAll("<", "Q");
		Middle=Middle.replaceAll("=", "K");
		Middle=Middle.replaceAll(">", "A");
		/*System.out.println(Top);
		System.out.println(Middle);
		System.out.println(Bottom);
		System.out.println(Arrays.toString(fangkuai));
		System.out.println(Arrays.toString(meihua));
		System.out.println(Arrays.toString(hongtao));
		System.out.println(Arrays.toString(heitao));
		System.out.println(Arrays.toString(shoupai));*/
		Result = Top+","+Middle+","+Bottom;
		System.out.println(Result);
		return Result;
	}
}
