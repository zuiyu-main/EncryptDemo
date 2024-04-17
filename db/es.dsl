put 127.0.0.1:9200/encrypt
{
    "settings": {
        "analysis": {
            "analyzer": {
                "my_analyzer": {
                    "tokenizer": "my_tokenizer"
                }
            },
            "tokenizer": {
                "my_tokenizer": {
                    "type": "pattern",
                    "pattern": "=="
                }
            }
        }
    },
    "mappings": {
        "_doc": {
            "properties": {
                "content": {
                    "type": "text"
                }
            }
        }
    }
}