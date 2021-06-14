# quality_challenge

### Exemplo de Request:
```json
{
    "propName": "Casinha Toppp",
    "propDistrict": "vila olímpia",
    "rooms":
    [
        {"roomName": "Sala de Jantar", "roomWidth": 3.87, "roomLength":2.53},
        {"roomName": "Cozinha", "roomWidth": 1.48, "roomLength":4.03},
        {"roomName": "Banheiro", "roomWidth": 1.18, "roomLength":2.53},
        {"roomName": "Quarto", "roomWidth": 3.58, "roomLength":2.53}
    ]
}
```


### Exemplo de Response:
```json
{
    "totalSquareMeters": 27.8,
    "propertyValue": 342197.07,
    "biggestRoom": {
        "roomName": "Sala de Jantar",
        "squareMeters": 9.79
    },
    "roomsSquareMeters": [
        {
            "roomName": "Sala de Jantar",
            "squareMeters": 9.79
        },
        {
            "roomName": "Cozinha",
            "squareMeters": 5.96
        },
        {
            "roomName": "Banheiro",
            "squareMeters": 2.99
        },
        {
            "roomName": "Quarto",
            "squareMeters": 9.06
        }
    ]
}
```

### Bairros disponíveis para teste: 

| Bairro       |   R$/mˆ  |
|--------------|:--------:|
| vila olímpia | 12310.0  |
| itaim bibi   | 11570.0  |
| pinheiros    | 11250.0  |
| vila madalena| 11340.0  |
| moóca        | 6860.0   |
| santana      | 6810.0   |
| tatuapé      | 6800.0   |
|parada Inglesa| 6790.0   |


