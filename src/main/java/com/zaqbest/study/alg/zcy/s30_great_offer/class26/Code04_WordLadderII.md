### 举个例子
start= "hit"  
end= "cog"  
wordlist = {"hot","dot","dog","lot","log","cog"}

### next map (枚举+set)
```
{
    "log": [
        "cog",
        "dog",
        "lot"
    ],
    "dot": [
        "dog",
        "hot",
        "lot"
    ],
    "hot": [
        "dot",
        "hit",
        "lot"
    ],
    "lot": [
        "dot",
        "log",
        "hot"
    ],
    "hit": [
        "hot"
    ],
    "cog": [
        "dog",
        "log"
    ],
    "dog": [
        "cog",
        "log",
        "dot"
    ]
}
{
    "log": 3,
    "dot": 2,
    "hot": 1,
    "lot": 2,
    "hit": 0,
    "cog": 4,
    "dog": 3
}

```

### 通过bfs生成 distance表
```
{
    "log": 3,
    "dot": 2,
    "hot": 1,
    "lot": 2,
    "hit": 0,
    "cog": 4,
    "dog": 3
}
```

#### distance表图示
![1660885427144.jpg](https://assets.zaqbest.com/2022/08/19/62ff19d632eed.jpg)