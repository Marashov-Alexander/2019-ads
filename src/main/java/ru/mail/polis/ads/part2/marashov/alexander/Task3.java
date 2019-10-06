package ru.mail.polis.ads.part2.marashov.alexander;

import java.util.Stack;

public class Task3 {

    private static Stack<TreeNode> treeStack;
    private static StringBuilder[] answer;

    private static class TreeNode {
        Character value;
        TreeNode right;
        TreeNode left;

        TreeNode(char value, TreeNode right, TreeNode left) {
            this.value = value;
            this.right = right;
            this.left = left;
        }
    }

    private static void buildTree(final String postfixForm) {
        treeStack.clear();

        for (int index = 0; index < postfixForm.length(); ++index) {
            final char current = postfixForm.charAt(index);
            if (Character.toLowerCase(current) == current) {
                treeStack.push(new TreeNode(current, null, null));
            } else {
                final TreeNode right = treeStack.pop();
                final TreeNode left = treeStack.pop();
                treeStack.push(
                        new TreeNode(current, right, left)
                );
            }
        }
    }

    private static int bufferTree(final TreeNode node, final int level) {
        int maxLevel = level;
        answer[level].append(node.value);
        if (node.right != null) {
            maxLevel = Math.max(maxLevel, bufferTree(node.right, level + 1));
        }
        if (node.left != null) {
            maxLevel = Math.max(maxLevel, bufferTree(node.left, level + 1));
        }
        return maxLevel;
    }

    public static void main(String[] args) {
        final FastScanner in = new FastScanner(System.in);

        treeStack = new Stack<>();
        answer = new StringBuilder[10001];

        final int t = in.nextInt();
        for (int i = 0; i < t; ++i) {
            final String postfixForm = in.next();
            buildTree(postfixForm);
            for (int j = 0; j < postfixForm.length(); ++j) {
                answer[j] = new StringBuilder();
            }
            final int maxLevel = bufferTree(treeStack.pop(), 0);
            for (int j = maxLevel; j >= 0; --j) {
                System.out.print(answer[j].toString());
            }
            System.out.println();
        }
    }

}
