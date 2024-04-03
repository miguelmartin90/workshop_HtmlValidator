import java.util.Queue;
import java.util.Stack;


public class HtmlValidator {

	public static Stack<HtmlTag> isValidHtml(Queue<HtmlTag> tags) {

		/*
		1. Escribir un método que determine si el HTML está bien formateado usando pilas
			Open tag  -> push: agregar el tag en el top de la pila
			Close tag -> pop: eliminar el tag que está en el top y retornarlo

			isValidHtml method: If the HTML file is well formatted, the method should return an empty Stack.
				return -> a stack of html tags

			> return null: to indicate that the file is not well format
			> return empty stack: when the file is well formatted
			--> Think about When to return null and when to return an empty Stack.

		 */
		/* IMPLEMENT THIS METHOD! */
		Stack<HtmlTag> tagsStack = new Stack<>();	// La pila de etiquetas tagsStack

		// Una instancia de la clase HtmlTag que recibirá el 1er elemento (etiqueta) de la cola
		// en cada iteración dentro del while
		HtmlTag tag;

		while (!tags.isEmpty()){
			tag = tags.poll();

			if (!tag.isSelfClosing()) {
				if (tag.isOpenTag()) {
					tagsStack.push(tag);
				}
				/* Si tenemos coincidencias entre la etiqueta que viene de la cola
				   y la etiqueta que está en el top de la pila, siempre que la pila no esté vacía,
				   eliminamos el top de la pila*/
				else if ( !(tagsStack.isEmpty()) && tag.matches(tagsStack.peek()) ) {
					tagsStack.pop();
				}
				/* HTML con errores-Caso 1: Si se encuentra en la cola una etiqueta de cierre
				   y la cola está vacía, retornamos la pila con valor null*/
				else if ( tagsStack.isEmpty() && !(tag.isOpenTag()) ){
					tagsStack = null;
					return tagsStack;
				}
				/* HTML con errores-Caso 2:Si no se cumple ninguna condición anterior, retornamos
				   la pila con las etiquetas que se hayan ingresado hasta el momento.*/
				else {
					return tagsStack;
				}
			}
		}
		return tagsStack; // this line is here only so this code will compile if you don't modify it
	}
}