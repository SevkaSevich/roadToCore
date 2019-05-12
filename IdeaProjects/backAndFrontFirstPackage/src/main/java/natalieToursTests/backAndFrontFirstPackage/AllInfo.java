package natalieToursTests.backAndFrontFirstPackage;

import java.util.ArrayList;

public class AllInfo {

	public static ArrayList<ArrayList<String>> getAdultInfo(){
		ArrayList<ArrayList<String>> allNeededTestInfo = new ArrayList<ArrayList<String>>();
        ArrayList<String> firstAdultInfo = new ArrayList<String>();
        firstAdultInfo.add("m");firstAdultInfo.add("savin");firstAdultInfo.add("vsevolod");
        firstAdultInfo.add("05051993");firstAdultInfo.add("4509123456");firstAdultInfo.add("12122020");firstAdultInfo.add("89162584758");
        allNeededTestInfo.add(firstAdultInfo);
        ArrayList<String> secondAdultInfo = new ArrayList<String>();
        secondAdultInfo.add("f");secondAdultInfo.add("suleimanova");secondAdultInfo.add("nina");
        secondAdultInfo.add("12121950");secondAdultInfo.add("1234567890");secondAdultInfo.add("12122020");secondAdultInfo.add("89168523695");
        allNeededTestInfo.add(secondAdultInfo);
        ArrayList<String> thirdAdultInfo = new ArrayList<String>();
        thirdAdultInfo.add("m");thirdAdultInfo.add("ivanov");thirdAdultInfo.add("ivan");
        thirdAdultInfo.add("30091985");thirdAdultInfo.add("9876543210");thirdAdultInfo.add("13122020");thirdAdultInfo.add("89162845173");
        allNeededTestInfo.add(thirdAdultInfo);
        ArrayList<String> fourthAdultInfo = new ArrayList<String>();
        fourthAdultInfo.add("f");fourthAdultInfo.add("ivanova");fourthAdultInfo.add("adelina");
        fourthAdultInfo.add("01011997");fourthAdultInfo.add("9514897523");fourthAdultInfo.add("14122020");fourthAdultInfo.add("89164871577");
        allNeededTestInfo.add(fourthAdultInfo);
        ArrayList<String> fifthAdultInfo = new ArrayList<String>();
        fifthAdultInfo.add("m");fifthAdultInfo.add("zenkov");fifthAdultInfo.add("mihail");
        fifthAdultInfo.add("02021991");fifthAdultInfo.add("4509789456");fifthAdultInfo.add("15112020");fifthAdultInfo.add("89168885544");
        allNeededTestInfo.add(fifthAdultInfo);
        ArrayList<String> sixAdultInfo = new ArrayList<String>();
        sixAdultInfo.add("m");sixAdultInfo.add("milashev");sixAdultInfo.add("dmitrii");
        sixAdultInfo.add("02021993");sixAdultInfo.add("4509777456");sixAdultInfo.add("15112022");sixAdultInfo.add("89168885566");
        allNeededTestInfo.add(sixAdultInfo);
        ArrayList<String> sevenAdultInfo = new ArrayList<String>();
        sevenAdultInfo.add("f");sevenAdultInfo.add("solyanikova");sevenAdultInfo.add("veronika");
        sevenAdultInfo.add("03031993");sevenAdultInfo.add("4509194227");sevenAdultInfo.add("29112022");sevenAdultInfo.add("89168887811");
        allNeededTestInfo.add(sevenAdultInfo);
        
        
		return allNeededTestInfo;
	}
	
	public static ArrayList<ArrayList<String>> getChildInfo(){
	ArrayList<ArrayList<String>> allNeededTestInfo = new ArrayList<ArrayList<String>>();
    ArrayList<String> firstChildInfo = new ArrayList<String>();
    firstChildInfo.add("m");firstChildInfo.add("proskurnin");firstChildInfo.add("ivan");
    firstChildInfo.add("4815162342");firstChildInfo.add("26112020");
    allNeededTestInfo.add(firstChildInfo);
    ArrayList<String> secondChildInfo = new ArrayList<String>();
    secondChildInfo.add("f");secondChildInfo.add("parshivkina");secondChildInfo.add("veronika");
    secondChildInfo.add("4509784569");secondChildInfo.add("27112020");
    allNeededTestInfo.add(secondChildInfo);
    ArrayList<String> thirdChildInfo = new ArrayList<String>();
    thirdChildInfo.add("m");thirdChildInfo.add("parshivkin");thirdChildInfo.add("vladislav");
    thirdChildInfo.add("6985124578");thirdChildInfo.add("28112020");
    allNeededTestInfo.add(thirdChildInfo);
    return allNeededTestInfo;
	}
	AllInfo(){}
	public static void main(String[] args){
		
	}
}
