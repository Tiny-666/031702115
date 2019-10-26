package GAME;

//import java.util.Arrays;

public class CHUPAI {
	static int[] fangkuai = new int[15];
	static int[] meihua = new int[15];
	static int[] hongtao = new int[15];
	static int[] heitao = new int[15];
	static int[] shoupai = new int[13];
	public static void Trans(String str)//ͳ������
	{
		String AllCard = str.replace(" ", "");//ȥ��ȫ���ո�
		//AllCard = str.replace("\"", "");//ȥ��ȫ������
		AllCard = AllCard.replace("10", ":");//��10�滻��:
		AllCard = AllCard.replace("J", ";");//��J�滻��;
		AllCard = AllCard.replace("Q", "<");//��Q�滻��<
		AllCard = AllCard.replace("K", "=");//��K�滻��=
		AllCard = AllCard.replace("A", ">");//��A�滻��>
		for(int i = 0; i < AllCard.length(); i += 2)//ͳ������
		{
			String Tmp = AllCard.substring(i, i+2);
			//Tmp.charAt(0)�ǻ�ɫ
			//Tmp.charAt(1)������
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
	public static String GetACard(int i)//ȡ��һ���Ƽ����Ӧ��ɫ��ĩβ�����ո�
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
	public static String GetAPair(int j)//ȡ��һ�ԣ�ĩβ�����ո�
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
	public static String GetTwoPairs(int p1,int p2)//ȡ���ԣ�p2�ǽ�С��
	{
		String Bottom="\"";
		Bottom+=GetAPair(p2)+" ";
		Bottom+=GetAPair(p1);
		int i;
		for(i=0;i<=12;i++)//�ҵ����Ժ���1�ŵ���
		{
			if(shoupai[i]==1)
			{
				if(p1<i)//�ϴ�Ķ�������ȵ�������С�����ƼӺ���
				Bottom+=" "+GetACard(i)+"\"";
				else if(p2>i)//��С�Ķ�������ȵ�������󣬵��Ƽ�ǰ��
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
		if(i==13)//û�ҵ�����
		{
			for(i=0;i<=12;i++)//�����
			{
				if(shoupai[i]>0)
				{
					if(p1<i)//�ϴ�Ķ�������ȵ�������С�����ƼӺ���
					Bottom+=" "+GetACard(i)+"\"";
					else if(p2>i)//��С�Ķ�������ȵ�������󣬵��Ƽ�ǰ��
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
	public static String Get_Gourd_Pair(int tri,int j)//ȡ����«��һ�ԣ�ĩβ�����ո�
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
		//û�ƻ����߸���û��ͬ��ʱ�������������
		if(count==1)//ֻ�ҵ����ţ���Ҫ��ԭ��ȥ(�ƻ���ͬ��) 	  //1.shoupai[j]=2,�����Ķ���,����һ���ƻ���ͬ��
															  //2.shoupai[j]=3,�ƻ�������ͬ���������ܣ�
		{
			if(fk==1)
			{
				fangkuai[j]=1;
				fangkuai[13]+=1;
			}
			else if(mh==1)//ֻ��Ҫ��ԭһ�ţ��ʿ��Լ���else���ӿ��ٶ�
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
		for(int i=0;i<13;i++)//�ж���û������˳��
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
		if(max>=5)//������˳�ӣ������ó���
		{
			if(shoupai[j]==2)//ֻ�Ǹ����ӣ�ֱ���ó���
				return GetAPair(j);
		}
		//û������˳��
		
		return "NULL";
	}
	public static String GetATri(int i)//ȡ��������ĩβ�����ո�
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
		shoupai[i]-=3;//�۳���Ӧ��
		return Bottom;
	}
	public static int Link_Double()//�ж����ԣ����ؽ�С���ӵ��±꣬û���򷵻�-1
	{
		for(int i=12;i>=1;i--)
		{
			if(shoupai[i]==2&&shoupai[i-1]==2)
				return i-1;
		}
		return -1;
	}
	public static int Trible()//�ж��Ƿ���������ͬ�����У����������±ꡣû�з���-1
	{
		int i;
		for(i=12;i>=0;i--)
		{
			if(shoupai[i]==3)
			break;
		}
		return i;
	}
	public static String THS(int[] arr,String huase)//������ɫͬ��˳���жϣ�����ȡ�����ģ�û�з��ء�NULL��
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
	public static String TH(int[] arr,String huase)//ȡ������ɫ������ͬ����������
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
	public static String Judge_THS()//�ж�������������û��ͬ��˳,����ȡ����û�з��ء�NULL��
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
	public static String Judge_Bomb()//�ж�������������û��ը����������ͬ����ûƥ��ʣ�µĵ��ƣ���ĩβ�����ո�����ȡ����û�з��ء�NULL��
	{
		String Bottom="";
		int i;
		for(i=12;i>=0;i--)//�Ӵ��ƿ�ʼ��
		{
			if(shoupai[i]==4)//�ҵ�������ͬ��,ֻ��һ��
			{
				Bottom+="\"#"+(char)(i+50)+" *"+(char)(i+50)+" &"+
						(char)(i+50)+" $"+(char)(i+50);
				fangkuai[i]=meihua[i]=hongtao[i]=heitao[i]=0;
				fangkuai[13]-=1;
				meihua[13]-=1;
				hongtao[13]-=1;
				heitao[13]-=1;
				shoupai[i]-=4;//�۳���Ӧ��
				return Bottom;
			}
		}
		return "NULL";
	}
	public static String Judge_Gourd()//�ж�������������û�к�«������ȡ����û�з��ء�NULL��
	{
		String Bottom="",Pair="";
		int trible=Trible();
		int link=Link_Double();
		if(trible!=-1)//��������ͬ
		{
			//System.out.println(link);
			if(link==-1)//û������
			{
				for(int j=0;j<=12;j++)//ֱ����ʣ�µ���С�Ķ��ӣ���û�ܷ��أ�˵��û���ܴճɺ�«����
				{
					if(shoupai[j]==2)//�ҵ����Դճɺ�«����
					{
						Pair=Get_Gourd_Pair(trible,j);
						if(Pair!="NULL")
						{
							if(j>trible)//������������������
								Bottom+="\""+GetATri(trible)+" "+Pair+"\"";
								else//�����������������С
								Bottom+="\""+Pair+" "+GetATri(trible)+"\"";
								//System.out.println(Arrays.toString(shoupai));
								return Bottom;
						}
					}
				}
				for(int j=0;j<=12;j++)//ֱ����ʣ�µ���С�Ķ��ӣ���û�ܷ��أ�˵��û���ܴճɺ�«����
				{
					if(shoupai[j]>2&&j!=trible)//�ҵ����Դճɺ�«����
					{
						Pair=Get_Gourd_Pair(trible,j);
						//System.out.println(Pair);
						if(Pair!="NULL")
						{
							if(j>trible)//������������������
								Bottom+="\""+GetATri(trible)+" "+Pair+"\"";
								else//�����������������С
								Bottom+="\""+Pair+" "+GetATri(trible)+"\"";
								return Bottom;
						}
					}
				}
			}
			else//������
			{
				for(int j=0;j<=12;j++)//��ʼ��ʣ�µ���С�Ķ���,��û�ܷ��أ�˵��ֻ�����ԣ�û����������
				{
					if(j==link)//�������ԣ�����
					{
						j+=1;
						continue;
					}
					else if(shoupai[j]==2)//һ�����ӣ������������е�һ�ԣ�ȡ��
					{
						Pair=Get_Gourd_Pair(trible,j);
						//System.out.println(Pair);
						if(Pair!="NULL")
						{
							if(j>trible)//������������������
								Bottom+="\""+GetATri(trible)+" "+Pair+"\"";
								else//�����������������С
								Bottom+="\""+Pair+" "+GetATri(trible)+"\"";
								//System.out.println(Arrays.toString(shoupai));
								return Bottom;
						}
					}
				}
				//ֻ�����ԣ�û���������ӣ�������
				Pair=Get_Gourd_Pair(trible,link);
				if(Pair!="NULL")
				{
					if(link>trible)//������������������
						Bottom+="\""+GetATri(trible)+" "+Pair+"\"";
						else//�����������������С
						Bottom+="\""+Pair+" "+GetATri(trible)+"\"";
					return Bottom;
				}
				else
				{
					Pair=Get_Gourd_Pair(trible,link+1);
					if(Pair!="NULL")
					{
						if(link>trible)//������������������
							Bottom+="\""+GetATri(trible)+" "+Pair+"\"";
							else//�����������������С
							Bottom+="\""+Pair+" "+GetATri(trible)+"\"";
						return Bottom;
					}
				}
			}
		}
		return "NULL";
	}
	public static String Judge_TH()//�ж�������������û��ͬ��������ȡ����û�з��ء�NULL��
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
	public static String Judge_Str()//�ж�������������û��˳�ӣ�����ȡ����û�з��ء�NULL��
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
	public static String Judge_Tri()//�ж�������������û������������ȡ����û�з��ء�NULL��
	{
		String Bottom="\"";
		int count=0;
		int trible=Trible();
		if(trible==-1)
			return "NULL";
		//System.out.println(trible);
		int flag=0;
		for(int i=0;i<=12;i++)//֮����2�ŵ���
		{
			if(shoupai[i]==1)
			{
				if(i>trible)
				{
					if(flag==0)//û������
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
					else//��������
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
	public static String Judge_TwoP(String st)//�ж�������������û�����ԣ��������ԣ�������ȡ����û�з��ء�NULL��
	{
		String Bottom="";
		int link=Link_Double();
		int p1=-1,p2=-1,p3=-1,p4=-1,p5=-1,count=0;
		for(int i=12;i>=0;i--)//�Ҷ��ӣ��Ӵ�Ŀ�ʼ
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
		if(count<2)//û�ҵ�����
			return "NULL";
		if(st=="Bottom")//�ǵ׶�
		{
			if(p4==-1)//ֻ�����Ի����ԣ�Ҫ�ֵ�ǰһ��һ��
				Bottom=Judge_Pair();
			else//���ĶԻ�����Լ����ԣ���ʱ���������ͣ�
			{
				if(link!=-1)//��������
					Bottom=GetTwoPairs(link+1,link);
				else//����������
					Bottom=GetTwoPairs(p1,p2);
			}
		}
		else//���ж�
		{
			if(p3==-1)//ֻ�����ԣ�Ҫ�ֵ�ǰ��һ��
				Bottom=Judge_Pair();
			else//�����Ի��Ķ�
			{
				if(link!=-1)//��������
					Bottom=GetTwoPairs(link+1,link);
				else//����������
					Bottom=GetTwoPairs(p1,p2);
			}
		}
		return Bottom;
	}
	public static String Judge_Pair()//�ж�������������û�ж��ӣ�����ȡ����û�з��ء�NULL��
	{
		String Bottom="\"";
		int j;
		int p=-1,count=0;
		for(j=12;j>=0;j--)//�Ҷ��ӣ������Ŀ�ʼ
		{
			if(shoupai[j]==2)//�ҵ�һ�ԣ�count+1
			{
				p=j;
				break;
			}
		}
		if(p==-1)//û�ҵ�����
			return "NULL";
		else//�ҵ�����
		{
			int flag=0;
			for(int i=0;i<=12;i++)//֮����3�ŵ���
			{
				if(shoupai[i]==1)
				{
					if(i>p)
					{
						if(flag==0)//û�Ӷ���
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
						else//���˶���
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
	public static String Judge_WuLong()//���Ͼ�û�У��ó���С�����ɢ��,�������
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
		//�ж�ͬ��˳
		if((str=Judge_THS())!="NULL")
		{
			//System.out.println(Arrays.toString(shoupai));
			return str;
		}
		//�ж�ը��
		if((str=Judge_Bomb())!="NULL")
		{
			//System.out.println(Arrays.toString(shoupai));
			return str;
		}
		//�жϺ�«
		if((str=Judge_Gourd())!="NULL")
		{
			//System.out.println(Arrays.toString(shoupai));
			return str;
		}
			
		//�ж�ͬ��
		if((str=Judge_TH())!="NULL")
		{
			//System.out.println(Arrays.toString(shoupai));
			str+="TH";
			return str;
		}
		//�ж�˳��
		if((str=Judge_Str())!="NULL")
		{
			//System.out.println(Arrays.toString(shoupai));
			return str;
		}
		//�ж�����
		if((str=Judge_Tri())!="NULL")
		{
			//System.out.println(Arrays.toString(shoupai));
			return str;
		}
		//�ж�����
		if((str=Judge_TwoP(dun))!="NULL")
		{
			//System.out.println(Arrays.toString(shoupai));
			return str;
		}
		//�ж϶���
		if((str=Judge_Pair())!="NULL")
		{
			//System.out.println(Arrays.toString(shoupai));
			return str;
		}
		//���Ͼ�û�У��ó�ɢ��
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
		Trans(str);//ͳ������
		//��ȡ�׶�
		Bottom=Get_Max("Bottom");
		//��ȡ�ж�
		Middle=Get_Max("Middle");
		//ʣ�µ����ó����ŵ������Լ���ը���Ĳ���
		int tri=Trible();
		int DOUB=-1;
		Top+="\"";
		if(tri!=-1)//��������ͬ
			Top+=GetATri(tri)+"\"";
		else//û��������ͬ
		{
			for(int i=12;i>=0;i--)
			{//�Ҷ���
				if(shoupai[i]==2)
				{//�ҵ�һ�����Ӻ��˳�ѭ��,�Ҳ���Ҳ����ν
					Top+=GetAPair(i);
					DOUB=i;
					break;
				}
			}
		}
		int count=0;
		for(int i=0;i<13;i++)//��ʱ����ʣ��һ�Ի���3��ȫ���ƻ�5��ȫ����
		{
			if(shoupai[i]>0)
			{
				int CNT=shoupai[i];
				for(int j=0;j<CNT;j+=1)
				{
					if(Bottom.length()==12)//�׶���ը�����һ�δ��ȫ
					{
						if((char)(i+50)>Bottom.charAt(2))//Ҫ�ӵĵ����������ը����ֱ�Ӽ��ں���
						Bottom+=" "+GetACard(i)+"\"";
						else//Ҫ�ӵĵ�������С��ը��������ǰ��
						{
							StringBuffer Str=new StringBuffer(Bottom);
							Str.insert(1,GetACard(i)+" " );
							Bottom=Str.toString();
							Bottom+="\"";
						}
					}
					else if(Middle.length()==12)//�ж���ը�����һ�δ��ȫ
					{
						if((char)(i+50)>Middle.charAt(2))//Ҫ�ӵĵ����������ը����ֱ�Ӽ��ں���
						Middle+=" "+GetACard(i)+"\"";
						else//Ҫ�ӵĵ�������С��ը��������ǰ��
						{
							StringBuffer Str=new StringBuffer(Middle);
							Str.insert(1,GetACard(i)+" " );
							Middle=Str.toString();
							Middle+="\"";
						}
					}
					else if(DOUB!=-1)//�жյ׶վ�ȫ���ж���
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
					else//�жյ׶վ�ȫ���޶���
					{
						Top+=GetACard(i);
						count++;
						if(count<3)//���ղ����ڶ���
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
		if(Bottom.length()>16&&Middle.length()>16)//������˫ͬ��,���ܻᷴˮ
		{
			int[] a=new int[13];
			int[] b=new int[13];
			int cnt=0;
			Bottom=Bottom.replace("\"", "");//ȥ��ȫ������
			Middle=Middle.replace("\"", "");//ȥ��ȫ������
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
				if(b[i]>a[i])//b�������б�a������Ԫ�أ����жմ��ڵ׶�ʱ����λ
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
		else if(Bottom.length()>16)//��ͬ��
			Bottom=Bottom.replaceAll("TH", "");
		else if(Middle.length()>16)//��ͬ��
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
