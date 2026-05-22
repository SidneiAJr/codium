dadosClientes.stream()
              .filter(c -> c.getTipoConta().equalsIgnoreCase("corrente"))
              .sorted(Comparator.comparingDouble(Conta::getSaldo).reversed())
              .limit(5);
