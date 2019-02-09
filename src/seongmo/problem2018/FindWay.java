package seongmo.problem2018;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

//길찾기 문제
public class FindWay {
	public static void main(String[] args) {
		int[][] nodeinfo = { { 5, 3 }, { 11, 5 }, { 13, 3 }, { 3, 5 }, { 6, 1 }, { 1, 3 }, { 8, 6 }, { 7, 2 },
				{ 2, 2 } };
		int[][] result = solution(nodeinfo);

		for (int[] arr : result) {
			System.out.println(Arrays.toString(arr));
		}
	}

	public static int[][] solution(int[][] nodeinfo) {
		int[][] answer = new int[2][nodeinfo.length];
		List<Node> tree = new LinkedList<Node>();
		for (int i = 0; i < nodeinfo.length; i++) {
			tree.add(new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1));
		}

		Collections.sort(tree, new Cmp());
		Node root = null;
		for (Node node : tree) {
			root = add(root, node);
		}

		cnt = 0;
		preOrder(root, answer[0]);
		cnt = 0;
		postOrder(root, answer[1]);

		return answer;
	}

	static class Cmp implements Comparator<Node> {

		@Override
		public int compare(Node n1, Node n2) {
			return n2.y - n1.y;
		}
	}

	static class Node {
		int x, y, index;
		Node left, right;

		public Node(int x, int y, int index) {
			this.x = x;
			this.y = y;
			this.index = index;
		}
	}
	//여기서 노드 반환안하고 그냥 void로 해서 수정만 했더니 call by value여서 인지 수정이안됨.
	public static Node add(Node parent, Node child) {
		if (parent == null) {
			return child;
		}
		if (child.x < parent.x) {
			parent.left = add(parent.left, child);
		} else {
			parent.right = add(parent.right, child);
		}

		return parent;
	}

	public static int cnt = 0;

	public static void preOrder(Node node, int[] answer) {
		if (node == null)
			return;

		answer[cnt++] = node.index;
		preOrder(node.left, answer);
		preOrder(node.right, answer);
	}

	public static void postOrder(Node node, int[] answer) {
		if (node == null)
			return;

		postOrder(node.left, answer);
		postOrder(node.right, answer);
		answer[cnt++] = node.index;
	}
}

