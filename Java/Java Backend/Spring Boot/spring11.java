private static final String ISSUER = "API Voll.med";

public String gerarToken(Usuario usuario) {
    try {
        var algoritmo = Algorithm.HMAC256(secret);
        return JWT.create()
                .withIssuer(ISSUER)
                .withSubject(usuario.getLogin())
                .withExpiresAt(dataExpiracao())
                .sign(algoritmo);
    } catch (JWTCreationException exception){
        throw new RuntimeException("erro ao gerar token jwt", exception);
    }
}

public String getSubject(String tokenJWT) {
    try {
        var algoritmo = Algorithm.HMAC256(secret);
        return JWT.require(algoritmo)
                .withIssuer(ISSUER)
                .build()
                .verify(tokenJWT)
                .getSubject();
    } catch (JWTVerificationException exception) {
        throw new RuntimeException("Token JWT inválido ou expirado!");
    }
}
