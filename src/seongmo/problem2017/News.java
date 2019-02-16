package seongmo.problem2017;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class News {
	
	public static int solution(String str1, String str2) {
	      int answer = 0;
	      
	      String[] first = str1.split("[^a-zA-Z]");
	      String[] second = str2.split("[^a-zA-Z]");
	      HashMap<String, Integer> map1 = new HashMap<>();
	      HashMap<String, Integer> map2 = new HashMap<>();
	      
	      for(int i = 0; i < first.length; i++) {
	    	  for(int j = 0; j < first[i].length(); j++) {
	    		  if(j + 1 >= first[i].length())
	    			  break;
	    		  String tmp = first[i].substring(j, j + 2).toUpperCase();
	    		  if(map1.containsKey(tmp)) {
	    			  map1.put(tmp, map1.get(tmp) + 1);
	    		  }
	    		  else {
	    			  map1.put(tmp, 1);
	    		  }
	    	  }
	      }
	      
	      for(int i = 0; i < second.length; i++) {
	    	  for(int j = 0; j < second[i].length(); j++) {
	    		  if(j + 1 >= second[i].length())
	    			  break;
	    		  String tmp = second[i].substring(j, j + 2).toUpperCase();
	    		  if(map2.containsKey(tmp)) {
	    			  map2.put(tmp, map2.get(tmp) + 1);
	    		  }
	    		  else {
	    			  map2.put(tmp, 1);
	    		  }
	    	  }
	      }
	      
	      if(map1.size() == 0 && map2.size() == 0) {
	    	  answer = 65536;
	      }
	      else {
	    	  answer = getJacard(map1, map2);
	      }
	      
	      return answer;
	  }
	
	static int getJacard(HashMap<String, Integer> map1, HashMap<String, Integer> map2) {
		
		int nGyo = 0;
		int nHab = 0;
		
		Set<String> key1 = map1.keySet();
		Set<String> key2 = map2.keySet();
		
		Set<String> hab = new HashSet<String>();
		for(String key: key1) {
			hab.add(key);
			if(key2.contains(key)) {
				nGyo += Math.min(map1.get(key), map2.get(key));
			}
		}
		for(String key: key2) {
			hab.add(key);
		}
		for(String key: hab) {
			if(key1.contains(key) && key2.contains(key)) {
				nHab += Math.max(map1.get(key), map2.get(key));
			}
			else {
				if(key1.contains(key)) {
					nHab += map1.get(key);
				}
				else {
					nHab += map2.get(key);
				}
			}
		}
		
		return (int)((nGyo / (float)nHab) * 65536);
	}
	
	public static void main(String[] args) {
		String str1 = "aa1+aa2";
		String str2 = "AAAA12";
		System.out.println(solution(str1, str2));
	}
}
