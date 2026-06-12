// LEGADO
function processarUsuario(id, callback) {
    buscarUsuario(id, function(err, usuario) {
        if (err) {
            callback("Erro ao buscar", null);
        } else {
            buscarPedidos(usuario.id, function(err, pedidos) {
                if (err) {
                    callback("Erro ao buscar pedidos", null);
                } else {
                    calcularDesconto(pedidos, function(err, total) {
                        if (err) {
                            callback("Erro no desconto", null);
                        } else {
                            callback(null, { usuario: usuario.nome, total: total });
                        }
                    });
                }
            });
        }
    });
}
