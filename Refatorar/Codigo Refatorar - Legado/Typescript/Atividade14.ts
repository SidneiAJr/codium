// LEGADO
function verificarStatus(p: any): any {
    if (p.status === 1) {
        return "Ativo";
    } else if (p.status === 2) {
        return "Inativo";
    } else if (p.status === 3) {
        return "Suspenso";
    }
}
