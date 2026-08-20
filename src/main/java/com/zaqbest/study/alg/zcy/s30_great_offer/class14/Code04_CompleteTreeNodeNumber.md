**计算过程示意图**  
![](https://assets.zaqbest.com/2022/05/12/627ccb9c29002.png)

计算步骤
- 获得树的最大高度
从左树遍历到叶子节点，就可以获得树的最大高度 allLevel

- 获得右子树的最大高度
右子树沿着左边界访问到叶子节点，获得右树的最大层数mostLeftLevel

- 根据情况分别处理  
  - 情况1：  
    allLevel == mostLeftLevel + 1，说明右子树是满二叉树

  - 情况2：  
    allLevel == mostLeftLevel， 说明左子树是满二叉树

满二叉树的节点数, 高度为h  
$N = 2^h - 1$

### 举个例子
![WechatIMG122.png](https://assets.zaqbest.com/2022/06/09/62a1f6b3901de.png)