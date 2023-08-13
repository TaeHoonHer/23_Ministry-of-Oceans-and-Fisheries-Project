# TaeHoon
## 23/08/06
Recommender systems are AI technologies that predict and suggest products, services, or content that users might be interested in. They play a crucial role in modern internet-based services, making it easier for users to access more information and products

Relevant Techniques
Recommender systems employ several key techniques, namely collaborative filtering, content-based filtering, and hybrid models. Collaborative filtering recommends items by analyzing user interactions and finding similarities among users or items. Content-based filtering analyzes item content to understand user preferences and similarity. Hybrid models combine collaborative filtering and content-based filtering to enhance the system's performance

Data Collection and Preprocessing
Recommender systems rely on large amounts of user behavior data, including purchases and ratings. Data preprocessing involves handling missing values, removing outliers, and scaling to improve data quality

Model Design
- User-Based Collaborative Filtering
  - Analyzes user behavior patterns to measure user similarities, often using methods like Pearson correlation, cosine similarity, or Jaccard similarity
  - Recommends items to a user based on what similar users have liked
  
- Item-Based Collaborative Filtering
  - Measures similarity between items based on user ratings
  - Recommends similar items to those the user has interacted with or liked
  
- Content-Based Filtering
  - Vectorizes item content information and creates user profiles to represent their preferences
  - Recommends items that match the user's profile based on content similarity

- Hybrid Models
  - Combines predictions from collaborative filtering and content-based filtering to achieve personalized recommendations
  - Solves the recommendation bias of collaborative filtering and the cold-start problem of content-based filtering

Learning Process
- User-Based and Item-Based Collaborative Filtering
  - Typically use matrix factorization techniques for training
  
- Content-Based Filtering
  - Calculates similarity between content vectors and user profiles

- Hybrid Models
  - Learns by combining predictions from collaborative and content-based filtering with weighted sums

Performance Evaluation
Recommender systems are evaluated using metrics like accuracy, precision, recall, F1 score, etc. A/B testing with real users is also important for assessing user satisfaction

recommender systems utilize various AI models to provide personalized recommendations based on user behavior data and content information. By employing different techniques and hybrid models, these systems enhance user experience and satisfaction by suggesting relevant items or content. As the field continues to evolve, ongoing research and advancements are needed to develop more sophisticated and dynamic recommendation systems that cater to individual preferences and user needs


## 23/08/13
Recommender algorithms are systems that propose suitable items based on a user's personal preferences or past behavior patterns. AI-based recommender algorithms can be broadly categorized into **Collaborative Filtering**, **Content-based Filtering**, and **Hybrid Methods**.

- **Collaborative Filtering**
  - Recommendations are generated based on the interactions between users and items (e.g., ratings, purchase history). This method recommends by finding similarities between users or items.

- **Content-based Filtering**
  - Recommendations are generated based on the attributes of items (e.g., the genre or actors of a movie) and user profile information.

- **Hybrid Methods**
  - This approach combines both Collaborative Filtering and Content-based Filtering.

### Example code
``` python
import numpy as np
user_item_matrix = np.array([
    [4, 0, 2, 1],
    [0, 2, 3, 4],
    [1, 0, 5, 3],
    [2, 5, 0, 0]
])

def cosine_similarity(v1, v2):
    dot_product = np.dot(v1, v2)
    norm_v1 = np.linalg.norm(v1)
    norm_v2 = np.linalg.norm(v2)
    return dot_product / (norm_v1 * norm_v2)

def recommend(user, user_item_matrix):
    similar_scores = {}
    
    for i in range(user_item_matrix.shape[1]):
        if user_item_matrix[user][i] == 0:
            for j in range(user_item_matrix.shape[1]):
                if user_item_matrix[user][j] != 0:
                    similarity = cosine_similarity(user_item_matrix[:, i], user_item_matrix[:, j])
                    similar_scores[i] = similar_scores.get(i, 0) + (similarity * user_item_matrix[user][j])

    recommended_item = max(similar_scores, key=similar_scores.get)
    return recommended_item

user = 0
print(f"User {user}에게 추천: {recommend(user, user_item_matrix)}")
```

### Matrix factorization techniques for recommender systems
- This paper emphasizes matrix factorization, one of the critical approaches in recommender system

#### First
- The paper emphasizes the significance of recommender systems and their applications, particularly highlighting the importance of information retrieval and filtering in large datasets
- The role of recommender systems as a primary tool for personalized information provision is discussed

#### Basics of Matrix Factorization
- The user-item rating matrix is represented as a large matrix with most values missing. The goal is to decompose this matrix into two smaller matrices
- These smaller matrices represent the "latent features" of users and items. These features can reflect the preferences of users and the characteristics of items

#### Advantages of Matrix Factorization
- Matrix factorization is very effective in predicting missing values
- It is computationally efficient and can be applied to large datasets
- Latent features can capture various attributes of users and items

#### Matrix Factorization Algorithms
- The paper describes matrix factorization algorithms such as Alternating Least Squares (ALS) and Stochastic Gradient Descent (SGD)
- It discusses the advantages and disadvantages of each algorithm and the best practices for specific scenarios

#### Regularization and Overfitting Prevention
- Matrix factorization can be susceptible to overfitting
- The paper introduces various regularization techniques to address this issue

#### Real-world Applications
- The success story of matrix factorization in the Netflix Prize competition is described. Matrix factorization was a key algorithm in this competition, achieving high predictive accuracy

#### Conclusion and Future Research Directions
- The paper summarizes the advantages and limitations of matrix factorization techniques
- It suggests future research and improvement directions, especially focusing on handling high-dimensional data and complex interaction patterns more effectively
