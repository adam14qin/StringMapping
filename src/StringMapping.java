import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
������һ���ַ���s�����ܲ���ת������һ���ַ���p��������ÿ��ת��Ҫ��������ͬ����ĸһ��䶯
������: abca(����aһ���) -> dbcd(��c) -> dbed(��b) -> dced������abca��ת����dced��return true��
 */

public class StringMapping {
	Set<Character> inloop=new HashSet<>();
	
	public boolean canTransfer(String s, String t){
		if(s.length()!=t.length()) return false;
		if(s.equals(t)) return true;
		HashSet<Character> union=new HashSet<>();
		HashMap<Character, Character> map=new HashMap<>();
		for (int i=0;i<s.length();i++){
			char a=s.charAt(i);
			char b=t.charAt(i);
			union.add(a);union.add(b);
			 if(map.containsKey(a)){
                if(map.get(a).equals(b))
                	continue;
                else
                	return false;
            }else{map.put(a,b);}       				
         }
		int loopNum=0;
		for (Character c:map.keySet()){
			if (!inloop.contains(c)){
				loopNum=hasLoop(loopNum, c, map);
			}
		}
		return loopNum<=26-union.size();		
	}
	
	public int hasLoop(int loopNum, char c, HashMap<Character, Character> map){
		Character start=c; 
		HashSet<Character> visited=new HashSet<>();
		while (map.containsKey(start)){//&& !start.equals(map.get(c))
			visited.add(start);
			start=map.get(start);
			if (start.equals(c)){
				loopNum++;
				inloop.addAll(visited);
				break;
			}
		}
		return loopNum;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringMapping s=new StringMapping();
		String str1="abca";
		String str2="dced";
		String str3="abcdefghijklmnopqrstuvwxyz";
		String str4="bcdefghijklmnopqrstuvwxyza";
		String str5="ab";
		String str6="ba";
		System.out.println(s.canTransfer(str1, str2));
		System.out.println(s.canTransfer(str3, str4));
		System.out.println(s.canTransfer(str5, str6));
	}

}
