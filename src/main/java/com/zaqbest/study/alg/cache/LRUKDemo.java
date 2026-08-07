package com.zaqbest.study.alg.cache;

/**
 * LRU-K算法 使用实例
 * K=2
 */
public class LRUKDemo {
    LRUK<Integer, Object> globalCache = new LRUK<>(100, 1000);
    public static void main(String[] args) {
        LRUKDemo demo  = new LRUKDemo();
        for(int i = 0; i < 1000; i++){
            demo.getData(i%5);
        }
    }

    /**
     * 数据查询
     * 先查询缓存，查询超过两次将数据缓存到cache
     *
     * @param key
     * @return
     */
    public Object getData(Integer key){
        Object result = globalCache.get(key);
        //缓存查找失败
        if (result == null){
            System.out.println("cache missed!!,key = "+key);
            //查询数据库
            result = getDataFromDb(key);
            if (result != null && globalCache.moveToCache(key)){
                globalCache.put(key, result);
            }
        } else{
            System.out.println("cache hit!!!,key="+key);
        }
        return result;
    }

    /**
     * 模拟查询数据库
     *
     * @param key
     * @return
     */
    private static Object getDataFromDb(int key) {
        System.out.println("database lookup!!!, key="+key);
        return 100;
    }
}
