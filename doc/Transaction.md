## 1 根据块hash获取当前交易信息列表


method：GET  
url:/transaction/getTransactionsByBlockHash?blockHash={blockHash}   
```json

successResponse： 

[
    {
        "txHash": "d28d9ca8b170fbdece854e74dc1dd9360ef97431609520ae696a125b703daf63",
        "blockHash": "00000000000000000024b3d4793dcbba032d3fc28a0d77a37d466b956fb68aa6",
        "time": "2019-06-15T02:01:44.008+0000",
        "amount": 10783.99
    },
    {
        "txHash": "f16822a3a35c1cd49a3a27c952cff12b1a4a4497d962a4c5fbc1bf3aa60945c2",
        "blockHash": "00000000000000000024b3d4793dcbba032d3fc28a0d77a37d466b956fb68aa6",
        "time": "2019-06-15T02:01:44.008+0000",
        "amount": 106.07
    }
]

```

| ResponseField     |     Type |   Description   | 
| :--------------: | :--------:| :------: |
|    txHash|   String |  交易hash 主键 |
|    blockHash|   String |  块hash(外键) |
|    time|   Date | 交易时间 |
|    amount(BTC)|   Double |  交易金额(比特币) |
|    amount(USD)|   Double | 交易金额(美元)|






