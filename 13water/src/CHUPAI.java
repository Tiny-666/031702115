package GAME;

//import java.util.Arrays;

//import java.util.Arrays;

public class CHUPAI {
	static int[] fangkuai = new int[15];
	static int[] meihua = new int[15];
	static int[] hongtao = new int[15];
	static int[] heitao = new int[15];
	static int[] shoupai = new int[13];
	public static void Trans(String AllCard)//ͳ������
	{
		for(int i = 0; i < 26; i += 2)//ͳ������
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
		}
		shoupai[j]-=2;//�۳���Ӧ��
		return Bottom;
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
		String str="\"";
		int count=0,CouNT=arr[13];
		//System.out.println(CouNT);
		for(int i=0;i<13;i++)
		{
			if(arr[i]>0)
			{
				if(CouNT-5>0)
					CouNT--;
				else
				{
					arr[i]-=1;
					arr[13]-=1;
					shoupai[i]-=1;
					str+=huase+(char)(i+50);
					if(count<4)
						str+=" ";
					else
					{
						str+="\"";
						break;
					}
					count++;
				}
			}
		}
		return str;
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
		String Bottom="";
		int trible=Trible();
		int link=Link_Double();
		//System.out.println(Arrays.toString(shoupai));
		if(trible!=-1)//��������ͬ
		{
			if(link==-1)//û������
			{
				for(int j=0;j<=12;j++)//ֱ����ʣ�µ���С�Ķ��ӣ���û�ܷ��أ�˵��û���ܴճɺ�«����
				{
					if(shoupai[j]==2)//�ҵ����Դճɺ�«����
					{
						if(j>trible)//������������������
						Bottom+="\""+GetATri(trible)+" "+GetAPair(j)+"\"";
						else//�����������������С
						Bottom+="\""+GetAPair(j)+" "+GetATri(trible)+"\"";
						//System.out.println(Arrays.toString(shoupai));
						return Bottom;
					}
				}
				for(int j=0;j<=12;j++)//ֱ����ʣ�µ���С�Ķ��ӣ���û�ܷ��أ�˵��û���ܴճɺ�«����
				{
					if(shoupai[j]>2&&j!=trible)//�ҵ����Դճɺ�«����
					{
						if(j>trible)//������������������
						Bottom+="\""+GetATri(trible)+" "+GetAPair(j)+"\"";
						else//�����������������С
						Bottom+="\""+GetAPair(j)+" "+GetATri(trible)+"\"";
						//System.out.println(Arrays.toString(shoupai));
						return Bottom;
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
						if(j>trible)//������������������
						Bottom+="\""+GetATri(trible)+" "+GetAPair(j)+"\"";
						else//�����������������С
						Bottom+="\""+GetAPair(j)+" "+GetATri(trible)+"\"";
						return Bottom;
					}
				}
				//ֻ�����ԣ�û���������ӣ�������
				if(link>trible)//������������������
				Bottom+="\""+GetATri(trible)+" "+GetAPair(link)+"\"";
				else//�����������������С
				Bottom+="\""+GetAPair(link)+" "+GetATri(trible)+"\"";
				return Bottom;
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
	public static String Judge_TwoP()//�ж�������������û�����ԣ��������ԣ�������ȡ����û�з��ء�NULL��
	{
		String Bottom="\"";
		int link=Link_Double();
		if(link!=-1)//��������
		{
			Bottom+=GetAPair(link)+" ";
			Bottom+=GetAPair(link+1);
			int i;
			for(i=0;i<=12;i++)//�ҵ����Ժ���1�ŵ���
			{
				if(shoupai[i]==1)
				{
					if(link+1<i)//�����нϴ�Ķ�������ȵ���С�����ƼӺ���
					Bottom+=" "+GetACard(i)+"\"";
					else//�����ǰ��
					{
						StringBuffer Str=new StringBuffer(Bottom);
						Str.insert(1,GetACard(i)+" " );
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
						if(link+1<i)//�����нϴ�Ķ�������ȵ���С�����ƼӺ���
						Bottom+=" "+GetACard(i)+"\"";
						else//�����ǰ��
						{
							StringBuffer Str=new StringBuffer(Bottom);
							Str.insert(1,GetACard(i)+" " );
							Bottom=Str.toString()+"\"";
						}
						break;
					}
				}
			}
		}
		else//����������
		{
			int j,p1=-1,p2=-1;
			int count=0;
			for(j=12;j>=0;j--)//�Ҷ��ӣ������Ŀ�ʼ
			{
				if(shoupai[j]==2)//�ҵ�һ�ԣ�count+1,����p1>p2
				{
					count++;
					if(count==1)
						p1=j;
					else if(count==2)
					{
						p2=j;
						break;
					}
				}
			}
			if(count<2)//û�ҵ�����
				return "NULL";
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
	public static String Get_Max()
	{
		String str;
		//�ж�ͬ��˳
		if((str=Judge_THS())!="NULL")
			return str;
		//�ж�ը��
		if((str=Judge_Bomb())!="NULL")
			return str;
		//�жϺ�«
		if((str=Judge_Gourd())!="NULL")
		{
			//System.out.println(Arrays.toString(shoupai));
			return str;
		}
			
		//�ж�ͬ��
		if((str=Judge_TH())!="NULL")
			return str;
		//�ж�˳��
		if((str=Judge_Str())!="NULL")
			return str;
		//�ж�����
		if((str=Judge_Tri())!="NULL")
			return str;
		//�ж�����
		if((str=Judge_TwoP())!="NULL")
			return str;
		//�ж϶���
		if((str=Judge_Pair())!="NULL")
			return str;
		//���Ͼ�û�У��ó�ɢ��
		if((str=Judge_WuLong())!="NULL")
			return str;
		return "NULL";
	}
	public String CardPlaying(String str) 
	{
		//String str="$A *4 $K &5 $9 &6 #A #2 *2 $2 $10 &8 &7";
		System.out.println(str);
		String Result ="";
		String AllCard = str.replace(" ", "");//ȥ��ȫ���ո�
		AllCard = AllCard.replace("10", ":");//��10�滻��:
		AllCard = AllCard.replace("J", ";");//��J�滻��;
		AllCard = AllCard.replace("Q", "<");//��Q�滻��<
		AllCard = AllCard.replace("K", "=");//��K�滻��=
		AllCard = AllCard.replace("A", ">");//��A�滻��>
		String Top ="";
		String Middle ="";
		String Bottom ="";
		Trans(AllCard);//ͳ������
		//��ȡ�׶�
		Bottom=Get_Max();
		//��ȡ�ж�
		Middle=Get_Max();
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