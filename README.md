## Product Search API with Elasticsearch & MongoDB
This project includes a product search API designed for e-commerce platforms. It leverages a modern, dual-database architecture to provide fast, configurable search capabilities and dynamic business rule management module.

# Core Features
*  # Intelligent Synonym Search:
    The API uses a custom synonym_analyzer in Elasticsearch to provide a smarter search experience. Users can search for a product using different terms, and the system will understand them. For example, a search for "refrigerator" will also return products named "fridge."
      Search Example:
      # Input: 
        Search for "refrigerator"
      # Output: 
        All matching products, including those named "Fridge."
   
<img width="975" height="793" alt="image" src="https://github.com/user-attachments/assets/819ed69a-0d7c-4abd-9245-4802405ece64" />

*  # Dynamic Business Rules:
    The application is integrated with a MongoDB-based ConfigService to manage business-specific rules. This allows product managers to update key information like "suggested brands" via an API without requiring any code changes or redeployments.
    For example, when searching for products in the "Home Appliances" category, the API dynamically fetches and returns a list of suggested brands such as Bosch, Miele, and Beko from the database.

<img width="776" height="368" alt="image" src="https://github.com/user-attachments/assets/2ec16ae5-1464-486b-b5b8-e66b45ec9ba2" />


    New config rules can be added via config api. 

<img width="975" height="473" alt="image" src="https://github.com/user-attachments/assets/532d5a40-0aab-4fd3-9cca-2acddaeb61ad" />


*  # Scalable Architecture:
      * MongoDB: Serves as the primary data store for all product information.
      * Elasticsearch: Optimized for real-time search, analytics, and auto-completion.
 
*  # Automated Setup:
      The project features a robust ElasticsearchIndexCreator that automatically creates and configures the products index at application startup. The index settings and mappings, including the synonym_analyzer, are defined in a dedicated es-settings-and-mappings.json file, ensuring consistency and easy management.


# How to Run

# 1. Prerequisites: 
  Ensure you have Java 17+, Maven, a running MongoDB instance, and an Elasticsearch instance.
# 2. Configuration:
  Update your database connection details in application.properties.
# 3. Build:
  Navigate to the project root and build the application using Maven.
# 4. Run: 
  Execute the Spring Boot application. The ElasticsearchIndexCreator will automatically set up your index.
# 5. Re-index Data:
  Re-index Data: Use your Logstash pipeline or a dedicated service to populate Elasticsearch with your product data.

Once the application is running, the API documentation is available at http://localhost:8080/swagger-ui.html

# How to import your csv file to ElasticSearch
# Use Logstash, create a conf file using below code part. 

# Then run this command on the terminal on your the directory which is installed logstash:   

C:\dev\logstash-9.1.2\bin> logstash -f C:\conf-file.conf


```
input {
  file {
    path => "C:/dev/products.csv"
    start_position => "beginning"
    sincedb_path => "NUL"
  }
}

filter {
  csv {
    separator => ","
    columns => ["_id","name","description","brand","category","price","currency","stock","ean","color","size","availability","internalId"]
  }

   if [_id] == "_id" {
    drop { }
  }

  mutate {
    convert => {
      "price" => "integer"
      "stock" => "integer"
      "internalId" => "integer"
    }
    remove_field => ["message", "event", "log", "host", "tags", "_id"]
  }
}

output {
  elasticsearch {
    hosts => ["http://localhost:9200"]
    index => "products"

```
  }
  stdout {
    codec => rubydebug
  }
}
