#### inserted result json for embed

```json
{
  "op": "c",
  "source": {
    "server_id": 0,
    "version": "1.4.1.Final",
    "file": "mysql-bin.000039",
    "connector": "mysql",
    "pos": 154,
    "name": "localhost-demo",
    "row": 0,
    "ts_ms": 0,
    "snapshot": "last",
    "db": "ordercenter",
    "table": "wxapp_scan_logs"
  },
  "after": {
    "store_code": "0090",
    "driver_mobile": "18719033514",
    "delivery_center": "深圳配送中心",
    "created_at": "2020-04-21T08:58:09Z",
    "confirm_time_type": "CHECKED",
    "version": 1,
    "driver_name": "炒着吃",
    "brevity_code": "5696",
    "updated_at": "2020-04-21T08:58:09Z",
    "store_name": "丽阳天下店",
    "code_value": "01w1234567#0087#184",
    "id": 39922,
    "scan_time": "2020-04-21T08:58:10Z"
  },
  "ts_ms": 1615445861547
}
```

### updated result json for embed

```json
{
  "op": "u",
  "before": {
    "uname": "xiaoh",
    "gender": 1,
    "uid": 2,
    "job": "teacher"
  },
  "source": {
    "thread": 3,
    "server_id": 1,
    "version": "1.4.1.Final",
    "file": "mysql-bin.000040",
    "connector": "mysql",
    "pos": 639,
    "name": "localhost-demo",
    "row": 0,
    "ts_ms": 1615519306000,
    "snapshot": "false",
    "db": "demo",
    "table": "tab_users"
  },
  "after": {
    "uname": "xiaoh2",
    "gender": 1,
    "uid": 2,
    "job": "teacher"
  },
  "ts_ms": 1615519306965
}
```

### deleted result json for embed

```json
{
  "op": "d",
  "before": {
    "uname": "xiaoh2",
    "gender": 1,
    "uid": 2,
    "job": "teacher"
  },
  "source": {
    "thread": 3,
    "server_id": 1,
    "version": "1.4.1.Final",
    "file": "mysql-bin.000040",
    "connector": "mysql",
    "pos": 957,
    "name": "localhost-demo",
    "row": 0,
    "ts_ms": 1615519377000,
    "snapshot": "false",
    "db": "demo",
    "table": "tab_users"
  },
  "ts_ms": 1615519377764
}
```