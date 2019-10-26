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
		int count=0,fk=0,mh=0,ht=0,heit=0,max=-1;
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
		//没破坏或者根本没有同花时，会在上面结束
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
		{
			if(shoupai[j]==2)//只是个对子，直接拿出来
				return GetAPair(j);
		}
		//没有其他顺子
		
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
	public static String TH(int[] arr,String huase)//取出单花色中最大的同花，并返回
	{
		StringBuffer str=new StringBuffer();
		str.insert(0, "\"");
		int count=0,CouNT=arr[13];
		//System.out.println(CouNT);
		for(int i=12;i>=0;i--)
		{
			if(arr[i]>0)
			{
				if(CouNT-5>0&&shoupai[i]>1)
					CouNT--;
				else
				{
					arr[i]-=1;
					arr[13]-=1;
					shoupai[i]-=1;
					str.insert(0, huase+(char)(i+50));
					count++;
					if(count<5)
						str.insert(0, " ");
					else
						break;
				}
			}
		}
		str.insert(0, "\"");
		String Str=str.toString();
		return Str;
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
	public static String Judge_Gourd()//判断整个手牌中有没有葫芦，有则取出，没有返回“NULL”
	{
		String Bottom="",Pair="";
		int trible=Trible();
		int link=Link_Double();
		if(trible!=-1)//有三张相同
		{
			//System.out.println(link);
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
								//System.out.println(Arrays.toString(shoupai));
								return Bottom;
						}
					}
				}
				for(int j=0;j<=12;j++)//直接找剩下的最小的对子，若没能返回，说明没有能凑成葫芦的牌
				{
					if(shoupai[j]>2&&j!=trible)//找到可以凑成葫芦的牌
					{
						Pair=Get_Gourd_Pair(trible,j);
						//System.out.println(Pair);
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
	public static String Judge_TH()//判断整个手牌中有没有同花，有则取出，没有返回“NULL”
	{
		String Bottom="NULL";
		int i;
		for(i=12;i>=0;i--)
		{
			if(shoupai[i]>0)
			{
				if(fangkuai[13]>4&&fangkuai[i]>0)
				{
					Bottom=TH(fangkuai,"#");
					break;
				}
				else if(meihua[13]>4&&meihua[i]>0)
				{
					Bottom=TH(meihua,"*");
					break;
				}
				else if(hongtao[13]>4&&hongtao[i]>0)
				{
					Bottom=TH(hongtao,"&");
					break;
				}
				else if(heitao[13]>4&&heitao[i]>0)
				{
					Bottom=TH(heitao,"$");
					break;
				}
			}
		}
		return Bottom;
	}
	public static String Judge_Str()//判断整个手牌中有没有顺子，有则取出，没有返回“NULL”
	{
		String Bottom="\"";
		for(int i=8;i>=0;i--)
		{
			if(shoupai[i]>0&&shoupai[i+1]>0&&shoupai[i+2]>0&&shoupai[i+3]>0&&shoupai[i+4]>0)
			{
				Bottom+=GetACard(i)+" ";
				Bottom+=GetACard(i+1)+" ";
				Bottom+=GetACard(i+2)+" ";
				Bottom+=GetACard(i+3)+" ";
				Bottom+=GetACard(i+4)+"\"";
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
	public static String Judge_TwoP(String st)//判断整个手牌中有没有两对（包括连对），有则取出，没有返回“NULL”
	{
		String Bottom="";
		int link=Link_Double();
		int p1=-1,p2=-1,p3=-1,p4=-1,p5=-1,count=0;
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
			}
		}
		if(count<2)//没找到两对
			return "NULL";
		if(st=="Bottom")//是底墩
		{
			if(p4==-1)//只有两对或三对，要分到前一墩一对
				Bottom=Judge_Pair();
			else//有四对或五对以及六对（此时是特殊牌型）
			{
				if(link!=-1)//存在连对
					Bottom=GetTwoPairs(link+1,link);
				else//不存在连对
					Bottom=GetTwoPairs(p1,p2);
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
					Bottom=GetTwoPairs(p1,p2);
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
		if((str=Judge_Gourd())!="NULL")
		{
			//System.out.println(Arrays.toString(shoupai));
			return str;
		}
			
		//判断同花
		if((str=Judge_TH())!="NULL")
		{
			//System.out.println(Arrays.toString(shoupai));
			str+="TH";
			return str;
		}
		//判断顺子
		if((str=Judge_Str())!="NULL")
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
		/**/
		//String str="#7 &2 &K #5 $7 $10 *8 $J &9 *K &6 #J $K";
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
		if(Bottom.length()>16&&Middle.length()>16)//出现了双同花,可能会反水
		{
			int[] a=new int[13];
			int[] b=new int[13];
			int cnt=0;
			Bottom=Bottom.replace("\"", "");//去除全部引号
			Middle=Middle.replace("\"", "");//去除全部引号
			Bottom=Bottom.replaceAll("TH", "");
			Middle=Middle.replaceAll("TH", "");
			Trans(Bottom);
			for(int i=12;i>=0;i--)
			{
				if(shoupai[i]==1)
				{
					a[cnt++]=i;
					shoupai[i]=0;
				}
			}
			cnt=0;
			Trans(Middle);
			for(int i=12;i>=0;i--)
			{
				if(shoupai[i]==1)
				{
					b[cnt++]=i;
					shoupai[i]=0;
				}
			}
			for(int i=0;i<5;i++)
			{
				if(b[i]>a[i])//b数组中有比a数组大的元素，即中墩大于底墩时，换位
				{
					String temp=Bottom;
					Bottom="\""+Middle+"\"";
					Middle="\""+temp+"\"";
					break;
				}
				else if(b[i]<a[i])
				{
					Bottom="\""+Bottom+"\"";
					Middle="\""+Middle+"\"";
					break;
				}
			}
		}
		else if(Bottom.length()>16)//单同花
			Bottom=Bottom.replaceAll("TH", "");
		else if(Middle.length()>16)//单同花
			Middle=Middle.replaceAll("TH", "");
		Top=Top.replaceAll(":", "10");
		Top=Top.replaceAll(";", "J");
		Top=Top.replaceAll("<", "Q");
		Top=Top.replaceAll("=", "K");
		Top=Top.replaceAll(">", "A");
		Bottom=Bottom.replaceAll(":", "10");
		Bottom=Bottom.replaceAll(";", "J");
		Bottom=Bottom.replaceAll("<", "Q");
		Bottom=Bottom.replaceAll("=", "K");
		Bottom=Bottom.replaceAll(">", "A");
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
