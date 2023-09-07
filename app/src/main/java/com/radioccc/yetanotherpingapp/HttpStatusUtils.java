package com.radioccc.yetanotherpingapp;

import java.util.HashMap;
import java.util.Map;

public class HttpStatusUtils {

    // Mapa que relaciona los códigos de estado con sus categorías y descripciones.
    private static final Map<Integer, String[]> httpStatusMap = new HashMap<Integer, String[]>() {{
        // Códigos 1xx - Respuestas informativas
        put(100, new String[]{"new String[]100", "Respuestas informativas", "Solicitud recibida, continúa procesándola"});
        put(101, new String[]{"101", "Respuestas informativas", "El servidor está cambiando los protocolos según la petición del cliente"});

        // Códigos 2xx - Respuestas exitosas
        put(200, new String[]{"200", "Respuestas exitosas", "La solicitud ha tenido éxito"});
        put(201, new String[]{"201", "Respuestas exitosas", "La solicitud ha sido completada con éxito y ha creado un nuevo recurso"});
        put(202, new String[]{"202", "Respuestas exitosas", "La solicitud ha sido aceptada para su procesamiento, pero aún no se ha completado"});
        put(204, new String[]{"204", "Respuestas exitosas", "La solicitud se ha completado con éxito, pero no devuelve ningún contenido"});
        put(205, new String[]{"205", "Respuestas exitosas", "El servidor ha reiniciado con éxito el contenido de la solicitud actual"});
        put(206, new String[]{"206", "Respuestas exitosas", "La solicitud se ha completado de manera parcial"});

        put(300, new String[]{"300", "Redirecciones", "La solicitud tiene más de una posible respuesta. El cliente debe elegir una"});
        put(301, new String[]{"301", "Redirecciones", "La página ha cambiado de ubicación permanentemente"});
        put(302, new String[]{"302", "Redirecciones", "La página ha cambiado de ubicación temporalmente"});
        put(303, new String[]{"303", "Redirecciones", "La respuesta a la solicitud se encuentra en un URI diferente y debe ser obtenida por GET"});
        put(304, new String[]{"304", "Redirecciones", "La solicitud de una página web ha sido condicional y el recurso no ha sido modificado"});
        put(305, new String[]{"305", "Redirecciones", "El agente de usuario debe configurar un proxy para acceder al recurso solicitado"});
        put(307, new String[]{"307", "Redirecciones", "La página ha cambiado de ubicación temporalmente, pero el método de la solicitud no debe cambiar"});
        put(308, new String[]{"308", "Redirecciones", "La página ha cambiado de ubicación permanentemente, pero el método de la solicitud no debe cambiar"});

        // Códigos 4xx - Errores del cliente
        put(400, new String[]{"400", "Errores del cliente", "La solicitud contiene sintaxis incorrecta o no puede procesarse"});
        put(401, new String[]{"401", "Errores del cliente", "Es necesario autenticarse o las credenciales proporcionadas no son válidas"});
        put(403, new String[]{"403", "Errores del cliente", "El servidor ha entendido la solicitud, pero la rechaza por razones desconocidas"});
        put(404, new String[]{"404", "Errores del cliente", "El recurso solicitado no se encuentra en el servidor"});
        put(405, new String[]{"405", "Errores del cliente", "El método de solicitud no está permitido para el recurso"});
        put(406, new String[]{"406", "Errores del cliente", "La respuesta no es aceptable según las cabeceras de solicitud"});
        put(407, new String[]{"407", "Errores del cliente", "Es necesario autenticarse en un proxy"});
        put(408, new String[]{"408", "Errores del cliente", "El servidor no ha recibido una solicitud en el tiempo esperado"});
        put(409, new String[]{"409", "Errores del cliente", "La solicitud entra en conflicto con el estado actual del recurso"});
        put(410, new String[]{"410", "Errores del cliente", "El recurso solicitado ya no está disponible y no dejará de estarlo"});
        put(411, new String[]{"411", "Errores del cliente", "La longitud requerida de la solicitud no se ha proporcionado"});
        put(412, new String[]{"412", "Errores del cliente", "Una o más condiciones establecidas en la solicitud no se han cumplido"});
        put(413, new String[]{"413", "Errores del cliente", "La solicitud es demasiado grande para ser procesada por el servidor"});
        put(414, new String[]{"414", "Errores del cliente", "La URI de la solicitud es demasiado larga para ser procesada por el servidor"});
        put(415, new String[]{"415", "Errores del cliente", "El formato de la solicitud no es compatible con el servidor"});
        put(416, new String[]{"416", "Errores del cliente", "La gama solicitada no se puede satisfacer por el servidor"});
        put(417, new String[]{"417", "Errores del cliente", "El servidor no puede cumplir con los requisitos de la cabecera de la solicitud"});
        put(418, new String[]{"418", "Errores del cliente", "Soy una tetera, no puedo hacer café"});
        put(421, new String[]{"421", "Errores del cliente", "La solicitud se ha dirigido a un servidor inapropiado"});
        put(422, new String[]{"422", "Errores del cliente", "La solicitud está bien formada, pero no se puede procesar debido a errores semánticos"});
        put(423, new String[]{"423", "Errores del cliente", "El recurso al que se hace referencia está bloqueado"});
        put(424, new String[]{"424", "Errores del cliente", "La solicitud falló debido a la falta de dependencias"});
        put(425, new String[]{"425", "Errores del cliente", "Demasiadas solicitudes, el usuario ha excedido el límite de tasa"});
        put(426, new String[]{"426", "Errores del cliente", "El servidor se niega a actualizar la solicitud usando el mismo protocolo"});
        put(428, new String[]{"428", "Errores del cliente", "El servidor necesita que la solicitud sea condicional"});
        put(429, new String[]{"429", "Errores del cliente", "El usuario ha enviado demasiadas solicitudes en un período de tiempo"});
        put(431, new String[]{"431", "Errores del cliente", "El servidor no está dispuesto a procesar la solicitud porque sus cabeceras son demasiado grandes"});
        put(451, new String[]{"451", "Errores del cliente", "El contenido ha sido eliminado como resultado de una orden judicial"});

        // Códigos 5xx - Errores del servidor
        put(500, new String[]{"500", "Errores del servidor", "Error interno del servidor"});
        put(501, new String[]{"501", "Errores del servidor", "El servidor no reconoce la solicitud o no puede cumplirla"});
        put(502, new String[]{"502", "Errores del servidor", "El servidor recibió una respuesta no válida desde el servidor ascendente al intentar cumplir la solicitud"});
        put(503, new String[]{"503", "Errores del servidor", "El servidor no está listo para manejar la solicitud"});
        put(504, new String[]{"504", "Errores del servidor", "El servidor no pudo obtener una respuesta oportuna desde el servidor ascendente o la puerta de enlace"});
        put(505, new String[]{"505", "Errores del servidor", "La versión HTTP utilizada en la solicitud no es compatible con el servidor"});
        put(506, new String[]{"506", "Errores del servidor", "La variante también se negocia"});
        put(507, new String[]{"507", "Errores del servidor", "El almacenamiento no es suficiente para completar la solicitud"});
        put(508, new String[]{"508", "Errores del servidor", "La solicitud no se puede procesar debido a conflictos de bucles infinitos"});
        put(510, new String[]{"510", "Errores del servidor", "La extensión del cliente necesita más recursos para completarse"});
        put(511, new String[]{"511", "Errores del servidor", "El cliente debe autenticarse para obtener la respuesta solicitada"});

    }};

    // Función para obtener información de un código de estado dado.
    public static String[] getHttpStatusInfo(int statusCode) {
        String[] info = httpStatusMap.get(statusCode);
        if (info != null) {
            return info;
        } else {
            return new String[]{"Código de estado no válido", "", ""};
        }
    }

    public static void main(String[] args) {
        // Ejemplo de uso: obtiene información para el código de estado 200.
        int statusCode = 200;
        String[] result = getHttpStatusInfo(statusCode);
        System.out.println("Número de código: " + result[0]);
        System.out.println("Categoría: " + result[1]);
        System.out.println("Descripción: " + result[2]);
    }
}
