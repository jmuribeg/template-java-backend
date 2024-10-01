RETO 2. CONSUMIR API GET Y FILTRAR LISTA
El api devuelve una lista de 100 publicaciones (posts) de una red social y el id del usuario que ha hecho el post. Cada usuario tiene 10 posts.
API_GET = https://jsonplaceholder.typicode.com/posts
Consideraciones
1.
Utiliza la clase PostsController para crear un servicio Rest que recibe
un argumento para buscar por id de usuario.
2.
El servicio Rest debe implementar una interfaz para el consumo del API GET.
3.
Realizar el cast del json de respuesta hacia una lista de objetos.
Objetivos
➢
Obtener el resultado del api get.
➢
Buscar los posts dónde el UserId sea igual al enviado.
Ejemplo
Input: GET: /api/user/3
Output:
[
{
"userId": 3,
"id": 21,
"title": "asperiores ea ipsam voluptatibus modi minima quia sint",
"body": "repellat aliquid praesentium dolorem quo\nsed totam minus"
},
{
"userId": 3,
"id": 22,
"title": "dolor sint quo a velit explicabo quia nam",
"body": "eos qui et ipsum ipsam suscipit aut\nsed omnis non"
},
{...}//8 posts restantes
]