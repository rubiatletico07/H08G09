## EL2b
# Explique las diferencias (si las ha habido) de los resultados obtenidos entre los dos programas de prueba.

Sí hay diferencias entre los dos programas.

En los dos programas se insertan los mismos números, del 0 al 128, por eso la suma total siempre es la misma: 8256.

La diferencia está en el orden en el que se insertan los datos.

En el primer programa los números se insertan en orden, de menor a mayor. Por eso el árbol queda muy desequilibrado, como si fuera una lista hacia la derecha. Esto hace que la altura sea muy grande y que el camino hasta algunos valores, como el 110, sea muy largo.

En el segundo programa los números se insertan de forma aleatoria. Por eso el árbol puede quedar más repartido entre la izquierda y la derecha. Normalmente la altura es menor y los caminos ser más cortos.

Por tanto, la suma no cambia, porque los datos son los mismos, pero sí cambian la altura del árbol y los caminos, porque cambia la forma del árbol.

# ¿Qué sucede con los resultados si ejecuta los programas de prueba varias veces?

En el primer programa, los resultados siempre son los mismos, porque los números siempre se insertan en el mismo orden: 0, 1, 2, ..., 128.

En el segundo programa, algunos resultados pueden cambiar cada vez que se ejecuta, porque el orden aleatorio puede ser distinto en cada ejecución.

Lo que no cambia en la prueba aleatoria es la suma total, porque siempre se insertan los mismos números. Lo que sí puede cambiar es la altura del árbol, la raíz y el camino hasta el valor 110, porque la estructura del árbol depende del orden en el que se insertan los datos.
