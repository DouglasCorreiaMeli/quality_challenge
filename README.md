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
```java
HashMap<String, Double> districts = new HashMap <>();

    // a chave é o nome do bairro 
    // o valor é R$ por mˆ2

  this.districts.put("vila olímpia", 12310.0);
  this.districts.put("itaim bibi", 11570.0);
  this.districts.put("pinheiros", 11250.0);
  this.districts.put("vila madalena", 11340.0);
  this.districts.put("moóca", 6860.0);
  this.districts.put("tatuapé", 6800.0);
  this.districts.put("santana", 6810.0);
  this.districts.put("parada Inglesa", 6790.0);
```

