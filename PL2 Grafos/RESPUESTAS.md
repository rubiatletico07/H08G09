1.¿Cuál es el camino mínimo entre dos entidades A y B del grafo?

El camino mínimo entre dos entidades A y B es el recorrido más corto que une A con B dentro del grafo
, el camino que pasa por el menor número de relaciones o aristas.

2.Dado un archivo de datos que se carga en el grafo, ¿genera un grafo disjunto?

Depende del grafo.
El grafo_conectado.json no genera un grafo disjunto, porque todos sus nodos quedan conectados.
El grafo_disjunto.json sí genera un grafo disjunto, porque hay varios grupos de nodos separados entre sí.

3.Suponiendo un grafo de conocimiento general con la información de los premios Nobel de todas las áreas, cómo harías para responder a la pregunta: ¿Qué físico famoso nació en la misma ciudad que Einstein?

Einstein nació en lugar:Ulm.
En el grafo creado, el físico que también nació en lugar:Ulm es:
persona:Hans Scholl
Por tanto, la respuesta obtenida por el programa es:
persona:Hans Scholl

4.Añada una tripleta <"persona:Antonio", "nace_en", "lugar:Villarrubia de los Caballeros"> al grafo. Liste cuáles son los lugares de nacimiento de los premios Nobel. ¿Qué caminos necesita recorrer para que su respuesta fuese correcta?

 Los caminos que hay que recorrer son: persona --premio--> premio:Nobel... y después persona --nace_en--> lugar así primero se comprueba quién tiene un Nobel y luego se mira dónde nació.

5.¿Qué tipos de nodos tiene el grafo?

Los tipos de nodos del grafo son: persona, premio, lugar, profesion, tipo

6.¿Qué es una ontología? ¿Qué relación tiene con los grafos? ¿Podríamos crear una ontología para nuestro problema? ¿Qué haríamos con ella?

Una ontología es una forma de definir los conceptos importantes de un tema y las relaciones que pueden existir entre ellos que sirve para dar estructura y significado a los datos.
Una ontología se puede representar como un grafo. Los conceptos son nodos y las relaciones son aristas. El grafo guarda la información concreta, y la ontología define cómo debe estar organizada esa información.
Sí, podríamos crear una ontología para este problema. Con ella definiríamos los tipos de nodos, las relaciones válidas y las reglas básicas del grafo. Serviría para organizar mejor los datos, validar las tripletas y evitar relaciones que no tengan sentido.