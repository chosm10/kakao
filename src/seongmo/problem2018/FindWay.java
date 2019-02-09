package seongmo.problem2018;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
//길찾기 문제
public class FindWay {
	public int[][] solution(int[][] nodeinfo) {
		int[][] answer = new int[2][nodeinfo.length];
		LinkedList<Node> list = new LinkedList<Node>();
		
		for(int i = 0; i < nodeinfo.length; i++) {
			list.add(new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1));
		}
		Collections.sort(list, new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				if(o1.y > o2.y)
					return -1;
				else if(o1.y < o2.y)
					return 1;
				else
					return 0;
			}
		});
		Node root = null;
		for(Node n: list) {
			root = add(root, n);
		}
		cnt = 0;
		preorder(root, answer[0]);
		cnt = 0;
		postorder(root, answer[1]);
	
		return answer;
	}
	
	public Node add(Node parent, Node child) {
		if(parent == null) {
			return child;
		}
		if(parent.x > child.x) {
			parent.l = add(parent.l, child);
		}
		else {
			parent.r = add(parent.r, child);
		}
		return parent;
	}
	public static int cnt;
	public void preorder(Node n, int[] bucket) {
		if(n == null)
			return;
		bucket[cnt++] = n.index;
		preorder(n.l, bucket);
		preorder(n.r, bucket);
	}
	public void postorder(Node n, int[] bucket) {
		if(n == null)
			return;
		postorder(n.l, bucket);
		postorder(n.r, bucket);
		bucket[cnt++] = n.index;
	}
	static class Node {
		public int x, y, index;
		public Node l, r;
		
		public Node(int x, int y, int index) {
			this.x = x;
			this.y = y;
			this.index = index;
		}
	}
}

