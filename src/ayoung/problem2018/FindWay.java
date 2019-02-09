package ayoung.problem2018;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

class Node {
	int data;			//노드 값
	int index;			//노드 번호
	Node left, right;

	public Node() {
		left = null;
		right = null;

	}
}

class NodeInfo {
	int[] nodedata;
	int index;
	public NodeInfo() {
		nodedata = new int[2];
	}
	
}

public class FindWay {

	static Node head = null;
	static int[][] answer;
	static int arrIndex = 0;
	
	public static void add(NodeInfo newdata) {
		
		Node newNode = new Node();
		newNode.data = newdata.nodedata[0];
		newNode.index = newdata.index;
	
		if(head==null) {	
			head = newNode;
		} else {
			Node temp = head;
			while(true) {
				// data(x)값에 오른쪽 왼쪽으로 이동하거나 할당
				if(temp.data < newNode.data) {
					if(temp.right == null) {
						temp.right = newNode;
						break;
					}
					temp = temp.right;
				}
				else {
					if(temp.left == null) {
						temp.left = newNode;
						break;
					}
					temp = temp.left;
				}
			}
		}
	}
	public static int[][] solution(int[][] nodeinfo) {
		int Nodelen = nodeinfo.length;
		answer = new int[2][Nodelen];
		
		//node 정보 index도 포함하기위해서 새로운 타입에 담아주기
		NodeInfo[] nodeInfos = new NodeInfo[Nodelen];
		for(int i=0;i<Nodelen;i++) {
			nodeInfos[i] = new NodeInfo();
			nodeInfos[i].nodedata = nodeinfo[i];
			nodeInfos[i].index = i+1;
		}
		Arrays.sort(nodeInfos, new Comparator<NodeInfo>() {
			@Override
			// y(레벨) 값에 따라 정렬하기 레벨 높은거 순으로 트리 추가하기 위해서
			public int compare(NodeInfo o1, NodeInfo o2) {
				// TODO Auto-generated method stub
				return o2.nodedata[1]-o1.nodedata[1];
			}
		});
		
		for(int i=0;i<Nodelen;i++) {
			add(nodeInfos[i]);
		}
	
		perorder(head);
		arrIndex = 0;
		postorder(head);
		
		return answer;
	}

	public static void perorder(Node node) {
		if(node == null) return;
		answer[0][arrIndex++] = node.index;
		perorder(node.left);
		perorder(node.right);
	}
	
	public static void postorder(Node node) {
		if(node == null) return;
		postorder(node.left);
		postorder(node.right);
		answer[1][arrIndex++] = node.index;
	}
	public static void main(String[] args) {
		int[][] nodeinfo = {{5,3},{11,5},{13,3},{3,5},{6,1},{1,3},{8,6},{7,2},{2,2}};
		
		solution(nodeinfo);
	}

}
