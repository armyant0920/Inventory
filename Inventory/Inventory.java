/*/J03 倉庫模擬系統
 * 字元問題尚未解決,中文字會產生2char寬度不知道,有沒有函數可以抓取"字元長度"以利判斷
 * */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class Inventory extends GetInputVer2 
{
   
  private static int tableLen=70;//版面長度設定
  private int ID;//料件號碼
  private String name;//料件名稱  
  private String Spec;//類別
  private int qty;//料件數量
  private  double price;//料件單價
  private String loc;//倉庫位置-暫時不用
  
   public Inventory()
   {this.name="empty";this.qty=0;}
   
   public Inventory(String name, int qty) 
   {this.name =name;
   this.qty = qty;
   /*/注意,這個例子故意使用同名的變數,故需用this抓private的原變數,
    * 從顏色可以看出兩者的區別
    */
   }

   public static void init(ArrayList<Inventory> array)
   { //創建出Inventory類型,並且屬性賦值 (預設資料而已,不重要,可以自己拿掉)
   Inventory I1 = new Inventory(); 
   I1.ID = 0005; 
   I1.name = "materialA";
   I1.Spec="catrgory1"; 
   I1.qty=10;
   I1.price = 10.0;
   I1.loc="warehouseA";
		   
   Inventory I2=new Inventory(); 
   I2.ID = 0002; 
   I2.name = "materialB";
   I2.Spec="catrgory2"; 
   I2.qty=20;
   I2.price = 20.0;
   I2.loc="warehouseB";
   
   Inventory I3=new Inventory(); 
   
   I3.ID = 0003; 
   I3.name = "materialC";
   I3.Spec="catrgory3"; 
   I3.qty=30;
   I3.price = 30.0;
   I3.loc="warehouseC";
   Inventory I4=new Inventory(); 
   //----------------------------
   I4.ID = 99; 
   I4.name = "materialC";
   I4.Spec="catrgory3"; 
   I4.qty=30;
   I4.price = 30.0;
   I4.loc="warehouseC";
   Inventory I5=new Inventory();
   I5.ID = 103; 
   I5.name = "materialC";
   I5.Spec="catrgory3"; 
   I5.qty=30;
   I5.price = 30.0;
   I5.loc="warehouseC";
   //-------------------
   
   //創建的初始Item類型變量,存儲到集合中 
   array.add(I1); 
   array.add(I2); 
   array.add(I3); 
   array.add(I4); 
   array.add(I5); 
  
}
	//------------------------------------------

public static void main(String[] args) throws IOException
 {//主程式,SHOW操作介面
	System.out.println("┴┬┴┬／￣＼＿／￣＼\r\n" + 
			"┬┴┬┴▏　　▏▔▔▔▔＼\r\n" + 
			"┴┬┴／＼　／　　　　　　﹨\r\n" + 
			"┬┴∕　　　　　　　／　　　）\r\n" + 
			"┴┬▏　　　　　　　　●　　▏\r\n" + 
			"┬┴▏　　　　　　　　　　　▔█◤\r\n" + 
			"┴◢██◣　　　　　　 ＼＿＿／\r\n" + 
			"┬█████◣　　　　　　　／\r\n" + 
			"┴█████████████◣\r\n" + 
			"◢██████████████▆▄\r\n" + 
			"◢████████████");
  System.out.println("=================================");
  System.out.println("J03_Kevin 倉儲系統試作");
	System.out.println("=================================");
  ArrayList<Inventory> array = new ArrayList<Inventory>(); 
  init(array);
  while(true)
  {//例行以ID排序
	  Inventory x;
		for(int i=0;i<array.size();i++)
		{for (int j=0;j<array.size()-1;j++)
			{if (array.get(j).ID>array.get(j+1).ID)
				{x=array.get(j);
				 array.set(j, array.get(j+1));
				 array.set(j+1, x);
				}		
			}
		}
	  
	  //調用選單方法 
  mainMenu(); 
  //調用用戶選擇序號方法 
  int choose = chooseFunction(); 
  switch (choose) 
  { 
  case 88:
  System.out.println("提醒您記得存檔再離開系統喔,按下0取消;按下1離開");
  if(GetInt(1)==0)
  {break;}
  else 
  {System.out.println("　╮　╮　╮\r\n" + 
  		"　　╰　╰　╰\r\n" + 
  		"┌───────┐\r\n" + 
  		"│　　　　├╮\r\n" + 
  		"│███████││\r\n" + 
  		"│███████││\r\n" + 
  		"│███████├╯\r\n" + 
  		"╰───────╯");}
  System.out.println("see u next time (^3^)");
  return ; //結束程式
  case 1: //調用1: 貨物 清單 
  showInventoryList(array); break; 
  case 2: //2: 添加新貨物編號 
  addInventory(array); break; 
  case 3: //3: 刪除貨物編號
  deleteInventory(array); break; 
  case 4: //4: 修改貨物 
  updateInventory(array); break; 
  case 5://5:進出貨功能(+/- 指定編號貨物數量)
  deposit(array);break;
  case 6://6:產生紀錄檔
  OUTPUT(array);break;
  case 7://7:讀取紀錄檔
  IMPORT(array);break;
  case 999://debug
  selectInventoryList(array);break;
  
  default: System.out.println("輸入的序號無效"); break; } } 
  }

public static void mainMenu()
{	 
	System.out.println(); 
	System.out.println("|================庫存管理系統介面================|"); 
	System.out.println("|88: 結束程式 1: 貨物清單 2: 添加貨物 3: 刪除貨物|\n|4: 修改貨物 5: 進出庫存 6: 匯出記錄  7: 匯入記錄|"); 
	System.out.println("|================================================|"); 
	System.out.println("請輸入要操作的功能序號"); 
	} 
//序號選擇方法。 根據接收到的功能選項，執行對應的操作。 
public static int chooseFunction()
	{return GetInt(3);}

public static void showInventoryList(ArrayList<Inventory> array)
{	int a,b;
	
	System.out.println("please input start ID");
	a=GetInt(4);
	System.out.println("please input end ID");
	b=GetInt(4);  
	
	String str=new String("ItemList");
	System.out.println(); 
	System.out.print("|"); 
	for (int i=1;i<=(tableLen-str.length())/2;i++)System.out.print("="); 
	System.out.print(str);
	for (int i=1;i<=(tableLen-str.length())/2;i++)System.out.print("="); 
	System.out.print("|"); 
	//System.out.println("|===============================庫存清單===============================|"); 
	
	System.out.println(); 
	System.out.println("|Item__ID|__ItemName__|__ItemSpec__|ItemQtys|____Price____|__Location__|"); 
	System.out.println("|========|============|============|========|=============|============|");
	//debug,表格格式標準化實驗用
	//遍歷集合 
	for(int i = 0 ; i < array.size(); i++)
	{Inventory item = array.get(i); 	
	//集合get方法,獲取出每個Inventory變量,可以使用Inventory接受get結果 
	if(item.ID>=a && item.ID<=b)
	//array.get(item.ID-1);System.out.println(array.);
	//Collections.sort(array, ID);
	//array.sort(item.ID);
	//變量item調用類中屬性 
		{System.out.print("|");System.out.print(item.ID);str=String.valueOf(item.ID);for(int x=1;x<(9-(str.length()));x++) {System.out.print("_");}System.out.print("|");
		System.out.print(item.name);str=item.name;for(int x=1;x<(13-(str.length()));x++) {System.out.print("_");}System.out.print("|");
		System.out.print(item.Spec);str=item.Spec;for(int x=1;x<(13-(str.length()));x++) {System.out.print("_");}System.out.print("|");
		System.out.print(item.qty);str=String.valueOf(item.qty);for(int x=1;x<(9-(str.length()));x++) {System.out.print("_");}System.out.print("|");
		System.out.print(item.price);str=String.valueOf(item.price);for(int x=1;x<(14-(str.length()));x++) {System.out.print("_");}System.out.print("|");
		System.out.print(item.loc);str=item.loc;for(int x=1;x<(13-(str.length()));x++) {System.out.print("_");}System.out.print("|");
		System.out.println();
		System.out.print("|");
		for (int x=1;x<=(tableLen);x++)System.out.print("="); 
		System.out.println("|");
		}
	}
}
//嘗試依選取ID編號PICK清單,未完工,測試系統資料碼999
public static void selectInventoryList(ArrayList<Inventory> array)//,int s(編號開始,int e(編號尾碼
{
	/*以ID排序:
	Inventory x;
	for(int i=0;i<array.size();i++)
	{for (int j=0;j<array.size()-1;j++)
		{if (array.get(j).ID>array.get(j+1).ID)
			{x=array.get(j);
			 //array.remove(j);
			 //array.add(x);
			 array.set(j, array.get(j+1));
			 array.set(j+1, x);
			}		
		}
	}*/

	int a,b;
	System.out.println("please input start ID");
	a=GetInt(4);
	System.out.println("please input end ID");
	b=GetInt(4);
	for(int i = 0 ; i < array.size(); i++)
	{ //集合get方法,獲取出每個Inventory變量,可以使用Inventory接受get結果 
	Inventory item = array.get(i); 	
	if(item.ID>=a && item.ID<=b)
		{System.out.print(array.indexOf(item));System.out.print("|");
		System.out.print(item.ID);System.out.print("|");
		System.out.print(item.name);System.out.print("|");
		System.out.print(item.Spec);System.out.print("|");
		System.out.print(item.qty);System.out.print("|");
		System.out.print(item.price);System.out.print("|");
		System.out.print(item.loc);System.out.print("|");
		System.out.println();
		} 
	}

 System.out.println(array.size());
 //array.clear();
}

public static void addInventory(ArrayList<Inventory> array)
//商品基本資訊輸入
{ System.out.println("選擇的是添加商品功能"); 
System.out.println("請輸入商品的編號(4碼)"); 
int ID = GetInt(4);
System.out.println("請輸入商品的名字(10碼)"); 
String name = GetString(10);
System.out.println("請輸入商品的規格(10碼)"); 
String Spec = GetString(10);
System.out.println("請輸入商品的庫存數量(4碼)"); 
int qty = GetInt(4);
System.out.println("輸入商品的單價(10碼)"); 
double price = GetDouble(10); 
System.out.println("請輸入商品存放位置(10碼)"); 
String loc = GetString(10);
//創建Inventory變量 
Inventory item=new Inventory();
//item.屬性賦值 
item.ID = ID; 
item.name = name; 
item.Spec=Spec;
item.qty = qty;
item.price = price;
item.loc =loc; 
array.add(item); 
//Collections.sort(item);
//Arrays.asList(array);
//Arrays.Sort
System.out.println("商品添加成功"); }
//商品刪除功能
public static void deleteInventory(ArrayList<Inventory> array)
{ 	System.out.println("選擇的是刪除功能"); 
	System.out.println("請輸入商品的編號"); 
	int ID = GetInt(4);
	//遍歷集合 
	for(int i = 0 ; i < array.size(); i++)
	{ //獲取到每個Inventory變量 
		Inventory item = array.get(i); 
		//變量,調用屬性ID,和用戶輸入的編號比較 
		if( item.ID == ID)
		{ 	System.out.print(array.indexOf(item));System.out.print("|");
			System.out.print(item.ID);System.out.print("|");
			System.out.print(item.name);System.out.print("|");
			System.out.print(item.Spec);System.out.print("|");
			System.out.print(item.qty);System.out.print("|");
			System.out.print(item.price);System.out.print("|");
			System.out.print(item.loc);System.out.print("|");
			System.out.println();
			System.out.println("指定編號內容如上,確定要刪除嗎?(0:取消 1:刪除)");
			int x=GetInt(1);
				if(x==0) {System.out.println("cancel");return;}
				else {array.remove(i);System.out.println("刪除成功");return;}
		}		
	}
	System.out.println("你輸入的編號不存在");
}  
 

public static void updateInventory(ArrayList<Inventory> array)
	{ 
	  System.out.println("選擇的是修改功能,需輸入密碼才可執行"); 
	  System.out.println("請輸入密碼"); 
	 
	  if (GetString(10).equals("a123")==false)
	  {System.out.println("密碼輸入有誤,結束工作");return;}
	   System.out.println("密碼輸入正確");
	   System.out.println("請輸入商品的編號"); 
	   int ID = GetInt(4);
	   //遍歷集合,獲取每個Inventory變量 
	   for(int i = 0 ; i < array.size(); i++)
	   {Inventory item = array.get(i); 
	    //獲取Inventory的屬性ID,和用戶輸入的ID比較
		if(item.ID == ID)
		{ System.out.println("輸入新的商品編號(4碼)"); 
		item.ID = GetInt(4);
		System.out.println("輸入新的商品名字(10碼)"); 
		item.name = GetString(10); 
		System.out.println("輸入新的商品規格(10碼)"); 
		item.Spec =GetString(10); 
		System.out.println("輸入新的商品數量(4碼)"); 
		item.qty = GetInt(4);
		System.out.println("輸入新的商品價格(10碼)"); 
		item.price = GetDouble(10);
		System.out.println("輸入新的存放位置(10碼)"); 
		item.loc = GetString(10); 
		System.out.println("商品修改成功"); 
		return ; 
		} 
	   } 
	   System.out.println("輸入的編號不存在"); 
	} 
public int GetID() 
	{return ID;}

public String GetMaterial() 
	{return name;}

public int GetQty() 
{return qty;}

public int deposit(int in) 
{qty += in;return qty;}

public int withdraw(int out) 
{qty -= out;return qty;}
public static void deposit(ArrayList<Inventory> array)
 { System.out.println("選擇的是進出庫功能"); 
 System.out.println("請輸入商品的編號(4碼)"); 
 int ID = GetInt(4); 
 //遍歷集合 
 for(int i = 0 ; i < array.size(); i++)
 {Inventory item = array.get(i);//獲取到每個Inventory變量 
	 if(item.ID == ID)
	 {System.out.println("您選定要修改的商品目前數量為:"); 
	  System.out.println(item.ID+" "+item.name+" "+item.qty); 
	  while(true) {
	  System.out.println("請輸入要增減的數量(含負號5碼):");
	  int inbound=GetInt(5);
	  if (item.qty+inbound <0) 
	  {System.out.println("調整後數量<0,請確認後重新輸入");}
	  else 
	  {System.out.println("變更前:"+" "+item.qty);
	   item.qty=item.qty+inbound;
	   System.out.println("變更後:"+" "+item.qty);
	   return;}
	  }
	 } 	 
  }
  System.out.println("輸入的編號不存在");
 }
//6.output功能
public static void OUTPUT(ArrayList<Inventory> array) throws IOException
	{
	 System.out.println("請輸入存檔名稱,路徑固定,最多20個字,請以英數輸入");
	 FileWriter fw = new FileWriter("D:\\eclipse-workspace\\Test\\"+GetString(20));//寫入指定的檔案，可設絕對路徑
	 for(int i = 0 ; i < array.size(); i++)
	{Inventory item = array.get(i); 
	 fw.write(item.ID+","+item.name+","+item.Spec+","+item.qty+","+item.price+","+item.loc+"\n");
	}
	
	fw.flush();//內容正式寫入，仍可繼續使用生成的FileWriter物件
	fw.close();
	System.out.println("檔案已匯出");
	}

//7.IMPORT功能
public static void IMPORT(ArrayList<Inventory> array) 
//可以在此按絕對目錄設定FileReader要讀取的檔案，須使用"\\"取代"\"
 {
  try 
  	{	
	  String str;
	  int ID,x,C=1;
	  boolean B1;//B2=false;//B1代表個體是否覆蓋,B2代表剩下的重複是否全部執行指定行動
	  
	  System.out.println("請輸入讀檔名稱(路徑固定,最多20碼,請以英數輸入");
	  FileReader fr = new FileReader("D:\\eclipse-workspace\\Test\\"+GetString(20));//"D:\\eclipse-workspace\\Test\\StockRecord"
	  BufferedReader br = new BufferedReader(fr);
	  while (br.ready())//當buffer就緒就印出buffer中的資料
	  {B1=true;
	   str=br.readLine();
		String[]S=str.split(",");
		ID=Integer.parseInt(S[0]);
		for(int i = 0 ; i < array.size(); i++)
		{Inventory Tempitem = array.get(i);
		 //System.out.println("create Temp");
		 if(Tempitem.ID == ID)
		 {System.out.println("sameIDcatch");
		  if (C==1)
		 	{System.out.println("輸入編號"+ID+":"+S[1]+"與現有資料:"+Tempitem.ID+":"+Tempitem.name+"重複,請確認");
			 System.out.println("1:不覆蓋 2:都不覆蓋 3:覆蓋  4:都覆蓋");
			 x=GetInt(1);
			 switch(x) 
			 {case 1:System.out.println("不覆蓋");B1=false;C=1;break;
			  case 2:System.out.println("都不覆蓋");B1=false;C=2;break;
			  case 3:System.out.println("覆蓋");B1=true;C=1;array.remove(i);break;
			  case 4:System.out.println("都覆蓋");B1=true;C=3;array.remove(i);break;
			  default :System.out.println("輸入錯誤");return;		  
			 }
		 	}
			else if(C==2) {B1=false;}	 
			else if (C==3) {B1=true;array.remove(i);}
		 }
		}	 
		//System.out.println("TAG1");
		if(B1==true)
			{	Inventory item=new Inventory();
		//item.屬性賦值 
				item.ID=Integer.parseInt(S[0]); 
				item.name = S[1]; 
				item.Spec=S[2];
				item.qty = Integer.parseInt(S[3]);
				item.price = Double.parseDouble(S[4]);
				item.loc =S[5];
				array.add(item);
				System.out.println("新增"+item.ID+item.name);
			} 
		}
	  fr.close();
	  System.out.println("匯入資料成功");
  	}
  
	catch(Exception e)
	{System.out.println("好像有問題,請確認檔案是否存在還是怎樣");}//所有問題時,不指定狀況
	finally{System.out.println("結束");}//不管有沒有例外, 結束前都到 finally 區塊
	
		
 }
}
/*須具備,未完成的功能項目:
1.物品刪除需做再確認,避免誤刪->OK
2.比照指定料號SHOW明細
3.重複商品編號的處理->寫入時OK
4.修改允許跳項
5.版面控制應全自動化:
訂出模板字元數上限
6.置中功能:
左右系統符號長度=(全長-"顯示文字長度")/2
7.針對不同商品種類 應能顯示不同欄位
條件
8.匯入資料->OK
(1)同樣ID的物品覆蓋/不覆蓋
(2)大量資料時,跳出之後比照處理的選項

9.依item.ID排序列印->OK
10.eclipse能一次顯示筆數有限制,勢必須要分頁能力-
11.excel輔助亂數資料生產->應java自動產生

12.應補新增商品的重複ID檢察功能
13.應嘗試將檢查重複ID功能改為調用方法
14.建立多個存檔備案?或者直接讓使用者可以命名存檔名稱->OK
15.讀檔需求同上,是否能讀指定檔名,或甚至直接命名?->OK

*
*
*
*/

 

		




