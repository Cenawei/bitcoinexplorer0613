## 1 获取当前最新区块列表


method：GET  
url:/block/getRecentBlocks   
```json

successResponse： 

[
    {
        "blockHash": "00000000000000000024b3d4793dcbba032d3fc28a0d77a37d466b956fb68aa6",
        "height": 580644,
        "time": "2019-06-14T13:42:21.519+0000",
        "txsize": 2390,
        "miner": "AntPool",
        "size": 1257767
    },
    {
        "blockHash": "00000000000000000024b3d4793dcbba032d3fc28a0d77a37d466b956fb68aa8",
        "height": 580643,
        "time": "2019-06-14T13:42:21.519+0000",
        "txsize": 2389,
        "miner": "F2Pool",
        "size": 1267767
    }
]

```

| ResponseField     |     Type |   Description   | 
| :--------------: | :--------:| :------: |
|    blockHash|   String |  区块hash |
|    height|   Integer |  区块高度 |
|    txsize|   Short | 交易数量 |
|    miner|   String | 矿工 |
|    size|   Integer |  区块大小 |
|    time|   Date | 出块时间|

## 2 根据区块hash获取区块详情


method：GET  
url:/block/getByBlockhash?blockHash={blockHash}; 
```json

successResponse： 

{
    "blockHash": "00000000000000000001ce5f88601a311f1c73c0073a15fe4e5956da7fbcd78b",
    "height": 580643,
    "size": 1322496,
    "difficulty": 7409399249090.25,
    "weight": null,
    "nextBlock": "00000000000000000024b3d4793dcbba032d3fc28a0d77a37d466b956fb68aa5",
    "prevBlock": "00000000000000000005ac7036789bfec28d230dff491f3382f6daf6523f5c44",
    "time": 1560520606682,
    "txsize": 2702,
    "outputTotal": null,
    "transactionFees": 8766.38,
    "merkleRoot": "07ac3d1c827b5c3ef69a7341bbdb2bf72339139b5f9e7e782d1bc82265b17798",
    "miner": null
}

```

| ResponseField     |     Type |   Description   | 
| :--------------: | :--------:| :------: |
|    blockHash|   String |  区块hash |
|    height|   Integer |  区块高度 |
|    size|   Integer | 区块大小 |
|    difficulty|   Double | 难度 |
|    weight|   Double |  区快重量 |
|    nextBlock|   String | 下一区块|
|    prevBlock|   String | 上一区块|
|    time|   Long | 出块时间|
|    txsize|   Short | 交易数量|
|    outputTotal|   Double | 总输出|
|    transactionFees|   Double | 交易费用|
|    merkleRoot|   String | 梅尔克树|
|    miner|   String | 矿工|



## 3 根据区块高度获取区块详情


method：GET  
url:/block/getByHeight?height={height}
```json

successResponse： 

{
    "blockHash": "00000000000000000001ce5f88601a311f1c73c0073a15fe4e5956da7fbcd79b",
    "height": 580643,
    "size": 1322496,
    "difficulty": 7409399249090.25,
    "weight": null,
    "nextBlock": "00000000000000000024b3d4793dcbba032d3fc28a0d77a37d466b956fb68aa5",
    "prevBlock": "00000000000000000005ac7036789bfec28d230dff491f3382f6daf6523f5c44",
    "time": 1560520748753,
    "txsize": 2702,
    "outputTotal": null,
    "transactionFees": 8766.38,
    "merkleRoot": "07ac3d1c827b5c3ef69a7341bbdb2bf72339139b5f9e7e782d1bc82265b17798",
    "miner": null
}

```

| ResponseField     |     Type |   Description   | 
| :--------------: | :--------:| :------: |
|    blockHash|   String |  区块hash |
|    height|   Integer |  区块高度 |
|    size|   Integer | 区块大小 |
|    difficulty|   Double | 难度 |
|    weight|   Double |  区快重量 |
|    nextBlock|   String | 下一区块|
|    prevBlock|   String | 上一区块|
|    time|   Long | 出块时间|
|    txsize|   Short | 交易数量|
|    outputTotal|   Double | 总输出|
|    transactionFees|   Double | 交易费用|
|    merkleRoot|   String | 梅尔克树|
|    miner|   String | 矿工|





