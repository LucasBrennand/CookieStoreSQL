import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ecommerce {

    // Credenciais de conexão ao banco de dados (substitua pelos seus)
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ecommerce";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    // Usuário atual do sistema
    private static String usuarioAtual;

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Cria o banco de dados se ele não existir
            criarBancoDeDados(connection);

            // Popula o banco de dados com dados iniciais
            popularBancoDeDados(connection);

            // Inicia o menu principal
            Scanner scanner = new Scanner(System.in);
            while (true) {
                mostrarMenuPrincipal();
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir o caractere de nova linha

                switch (opcao) {
                    case 1:
                        login(connection, scanner);
                        break;
                    case 2:
                        // Criar banco de dados
                        criarBancoDeDados(connection);
                        break;
                    case 3:
                        // Destruir banco de dados
                        destruirBancoDeDados(connection);
                        break;
                    case 0:
                        System.out.println("Saindo do sistema...");
                        return;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    // Criar banco de dados
    private static void criarBancoDeDados(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("DROP DATABASE IF EXISTS ecommerce");
        statement.execute("CREATE DATABASE ecommerce");
        System.out.println("Banco de dados criado com sucesso.");
        statement.close();
    }

    // Destruir banco de dados
    private static void destruirBancoDeDados(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("DROP DATABASE ecommerce");
        System.out.println("Banco de dados destruído com sucesso.");
        statement.close();
    }

    // Popular banco de dados com dados iniciais
    private static void popularBancoDeDados(Connection connection) throws SQLException {
        // Inserir 20 produtos
        PreparedStatement stmtProduto = connection.prepareStatement("INSERT INTO produto (nome, descricao, quantidade, valor) VALUES (?, ?, ?, ?)");
        String[] nomesProdutos = {"Biscoito", "Chocolate", "Bolo", "Doce", "Bala", "Torta", "Pão de Queijo", "Pastel", "Pizza", "Refrigerante",
                "Suco", "Café", "Leite", "Iogurte", "Ovo", "Queijo", "Presunto", "Salsicha", "Frango", "Carne"};
        for (int i = 0; i < 20; i++) {
            stmtProduto.setString(1, nomesProdutos[i]);
            stmtProduto.setString(2, "Descrição do " + nomesProdutos[i]);
            stmtProduto.setInt(3, 100);
            stmtProduto.setDouble(4, 10.00);
            stmtProduto.executeUpdate();
        }
        stmtProduto.close();

        // Inserir 5 cargos
        PreparedStatement stmtCargo = connection.prepareStatement("INSERT INTO funcionario (nome, idade, sexo, cargo, salario, nascimento) VALUES (?, ?, ?, ?, ?, ?)");
        String[] cargos = {"vendedor", "gerente", "CEO", "Caixa", "Estoquista"};
        for (int i = 0; i < 5; i++) {
            stmtCargo.setString(1, "Nome " + cargos[i]);
            stmtCargo.setInt(2, 30);
            stmtCargo.setString(3, "m");
            stmtCargo.setString(4, cargos[i]);
            stmtCargo.setDouble(5, 5000.00);
            stmtCargo.setDate(6, Date.valueOf("2000-01-01"));
            stmtCargo.executeUpdate();
        }
        stmtCargo.close();

        // Inserir 100 clientes
        PreparedStatement stmtCliente = connection.prepareStatement("INSERT INTO cliente (nome, sexo, idade, nascimento) VALUES (?, ?, ?, ?)");
        for (int i = 1; i <= 100; i++) {
            stmtCliente.setString(1, "Cliente " + i);
            stmtCliente.setString(2, "m");
            stmtCliente.setInt(3, 25);
            stmtCliente.setDate(4, Date.valueOf("1998-01-01"));
            stmtCliente.executeUpdate();
        }
        stmtCliente.close();
    }

    // Mostrar menu principal
    private static void mostrarMenuPrincipal() {
        System.out.println("\n--- Sistema de E-commerce ---");
        System.out.println("1. Login");
        System.out.println("2. Criar Banco de Dados");
        System.out.println("3. Destruir Banco de Dados");
        System.out.println("0. Sair");
        System.out.print("Digite a opção desejada: ");
    }

    // Login do usuário
    private static void login(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("\n--- Login ---");
        System.out.print("Usuário: ");
        String usuario = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        // Autenticação do usuário (implemente a lógica de validação da senha)
        if (usuario.equals("admin") && senha.equals("admin")) {
            usuarioAtual = "admin";
            mostrarMenuAdmin(connection, scanner);
        } else if (usuario.equals("gerente") && senha.equals("gerente")) {
            usuarioAtual = "gerente";
            mostrarMenuGerente(connection, scanner);
        } else if (usuario.equals("funcionario") && senha.equals("funcionario")) {
            usuarioAtual = "funcionario";
            mostrarMenuFuncionario(connection, scanner);
        } else {
            System.out.println("Usuário ou senha inválidos. Tente novamente.");
        }
    }

    // Menu do Administrador
    private static void mostrarMenuAdmin(Connection connection, Scanner scanner) throws SQLException {
        while (true) {
            System.out.println("\n--- Menu do Administrador ---");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Cadastrar Cliente");
            System.out.println("3. Gerenciar Funcionários");
            System.out.println("4. Gerenciar Vendas");
            System.out.println("5. Visualizar Estatísticas");
            System.out.println("0. Sair");
            System.out.print("Digite a opção desejada: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir o caractere de nova linha

            switch (opcao) {
                case 1:
                    cadastrarProduto(connection, scanner);
                    break;
                case 2:
                    cadastrarCliente(connection, scanner);
                    break;
                case 3:
                    gerenciarFuncionarios(connection, scanner);
                    break;
                case 4:
                    gerenciarVendas(connection, scanner);
                    break;
                case 5:
                    visualizarEstatisticas(connection);
                    break;
                case 0:
                    usuarioAtual = null;
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    // Menu do Gerente
    private static void mostrarMenuGerente(Connection connection, Scanner scanner) throws SQLException {
        while (true) {
            System.out.println("\n--- Menu do Gerente ---");
            System.out.println("1. Buscar Produtos");
            System.out.println("2. Buscar Clientes");
            System.out.println("3. Buscar Funcionários");
            System.out.println("4. Buscar Vendas");
            System.out.println("5. Editar Produtos");
            System.out.println("6. Editar Clientes");
            System.out.println("7. Editar Funcionários");
            System.out.println("8. Excluir Produtos");
            System.out.println("9. Excluir Clientes");
            System.out.println("10. Excluir Funcionários");
            System.out.println("0. Sair");
            System.out.print("Digite a opção desejada: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir o caractere de nova linha

            switch (opcao) {
                case 1:
                    buscarProdutos(connection, scanner);
                    break;
                case 2:
                    buscarClientes(connection, scanner);
                    break;
                case 3:
                    buscarFuncionarios(connection, scanner);
                    break;
                case 4:
                    buscarVendas(connection, scanner);
                    break;
                case 5:
                    editarProduto(connection, scanner);
                    break;
                case 6:
                    editarCliente(connection, scanner);
                    break;
                case 7:
                    editarFuncionario(connection, scanner);
                    break;
                case 8:
                    excluirProduto(connection, scanner);
                    break;
                case 9:
                    excluirCliente(connection, scanner);
                    break;
                case 10:
                    excluirFuncionario(connection, scanner);
                    break;
                case 0:
                    usuarioAtual = null;
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    // Menu do Funcionário
    private static void mostrarMenuFuncionario(Connection connection, Scanner scanner) throws SQLException {
        while (true) {
            System.out.println("\n--- Menu do Funcionário ---");
            System.out.println("1. Cadastrar Venda");
            System.out.println("2. Consultar Vendas");
            System.out.println("0. Sair");
            System.out.print("Digite a opção desejada: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir o caractere de nova linha

            switch (opcao) {
                case 1:
                    cadastrarVenda(connection, scanner);
                    break;
                case 2:
                    consultarVendas(connection, scanner);
                    break;
                case 0:
                    usuarioAtual = null;
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    // Cadastrar Produto
    private static void cadastrarProduto(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("\n--- Cadastrar Produto ---");
        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine();
        System.out.print("Descrição do produto: ");
        String descricao = scanner.nextLine();
        System.out.print("Quantidade em estoque: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine(); // Consumir o caractere de nova linha
        System.out.print("Valor do produto: ");
        double valor = scanner.nextDouble();
        scanner.nextLine(); // Consumir o caractere de nova linha

        PreparedStatement statement = connection.prepareStatement("INSERT INTO produto (nome, descricao, quantidade, valor) VALUES (?, ?, ?, ?)");
        statement.setString(1, nome);
        statement.setString(2, descricao);
        statement.setInt(3, quantidade);
        statement.setDouble(4, valor);
        statement.executeUpdate();
        statement.close();

        System.out.println("Produto cadastrado com sucesso!");
    }

    // Cadastrar Cliente
    private static void cadastrarCliente(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("\n--- Cadastrar Cliente ---");
        System.out.print("Nome do cliente: ");
        String nome = scanner.nextLine();
        System.out.print("Sexo do cliente (m/f/o): ");
        String sexo = scanner.nextLine();
        System.out.print("Idade do cliente: ");
        int idade = scanner.nextInt();
        scanner.nextLine(); // Consumir o caractere de nova linha
        System.out.print("Data de nascimento do cliente (AAAA-MM-DD): ");
        String nascimento = scanner.nextLine();

        PreparedStatement statement = connection.prepareStatement("INSERT INTO cliente (nome, sexo, idade, nascimento) VALUES (?, ?, ?, ?)");
        statement.setString(1, nome);
        statement.setString(2, sexo);
        statement.setInt(3, idade);
        statement.setDate(4, Date.valueOf(nascimento));
        statement.executeUpdate();
        statement.close();

        System.out.println("Cliente cadastrado com sucesso!");
    }

    // Gerenciar Funcionários
    private static void gerenciarFuncionarios(Connection connection, Scanner scanner) throws SQLException {
        while (true) {
            System.out.println("\n--- Gerenciar Funcionários ---");
            System.out.println("1. Cadastrar Funcionário");
            System.out.println("2. Buscar Funcionários");
            System.out.println("3. Editar Funcionário");
            System.out.println("4. Excluir Funcionário");
            System.out.println("5. Reajuste Salarial");
            System.out.println("0. Voltar");
            System.out.print("Digite a opção desejada: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir o caractere de nova linha

            switch (opcao) {
                case 1:
                    cadastrarFuncionario(connection, scanner);
                    break;
                case 2:
                    buscarFuncionarios(connection, scanner);
                    break;
                case 3:
                    editarFuncionario(connection, scanner);
                    break;
                case 4:
                    excluirFuncionario(connection, scanner);
                    break;
                case 5:
                    reajusteSalarial(connection, scanner);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    // Cadastrar Funcionário
    private static void cadastrarFuncionario(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("\n--- Cadastrar Funcionário ---");
        System.out.print("Nome do funcionário: ");
        String nome = scanner.nextLine();
        System.out.print("Sexo do funcionário (m/f/o): ");
        String sexo = scanner.nextLine();
        System.out.print("Idade do funcionário: ");
        int idade = scanner.nextInt();
        scanner.nextLine(); // Consumir o caractere de nova linha
        System.out.print("Cargo do funcionário (vendedor/gerente/CEO): ");
        String cargo = scanner.nextLine();
        System.out.print("Salário do funcionário: ");
        double salario = scanner.nextDouble();
        scanner.nextLine(); // Consumir o caractere de nova linha
        System.out.print("Data de nascimento do funcionário (AAAA-MM-DD): ");
        String nascimento = scanner.nextLine();

        PreparedStatement statement = connection.prepareStatement("INSERT INTO funcionario (nome, sexo, idade, cargo, salario, nascimento) VALUES (?, ?, ?, ?, ?, ?)");
        statement.setString(1, nome);
        statement.setString(2, sexo);
        statement.setInt(3, idade);
        statement.setString(4, cargo);
        statement.setDouble(5, salario);
        statement.setDate(6, Date.valueOf(nascimento));
        statement.executeUpdate();
        statement.close();

        System.out.println("Funcionário cadastrado com sucesso!");
    }

    // Buscar Funcionários
    private static void buscarFuncionarios(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("\n--- Buscar Funcionários ---");
        System.out.print("Digite o nome do funcionário (ou deixe em branco para buscar todos): ");
        String nome = scanner.nextLine();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM funcionario WHERE nome LIKE ?");
        statement.setString(1, "%" + nome + "%");
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            do {
                int id = resultSet.getInt("id");
                String nomeFuncionario = resultSet.getString("nome");
                String sexo = resultSet.getString("sexo");
                int idade = resultSet.getInt("idade");
                String cargo = resultSet.getString("cargo");
                double salario = resultSet.getDouble("salario");
                Date nascimento = resultSet.getDate("nascimento");

                System.out.println("ID: " + id);
                System.out.println("Nome: " + nomeFuncionario);
                System.out.println("Sexo: " + sexo);
                System.out.println("Idade: " + idade);
                System.out.println("Cargo: " + cargo);
                System.out.println("Salário: " + salario);
                System.out.println("Nascimento: " + nascimento);
                System.out.println("-------------------");
            } while (resultSet.next());
        } else {
            System.out.println("Nenhum funcionário encontrado.");
        }
        resultSet.close();
        statement.close();
    }

    // Editar Funcionário
    private static void editarFuncionario(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("\n--- Editar Funcionário ---");
        System.out.print("Digite o ID do funcionário: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir o caractere de nova linha

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM funcionario WHERE id = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String nome = resultSet.getString("nome");
            String sexo = resultSet.getString("sexo");
            int idade = resultSet.getInt("idade");
            String cargo = resultSet.getString("cargo");
            double salario = resultSet.getDouble("salario");
            Date nascimento = resultSet.getDate("nascimento");

            System.out.println("Nome atual: " + nome);
            System.out.print("Novo nome: ");
            String novoNome = scanner.nextLine();
            if (!novoNome.isEmpty()) {
                nome = novoNome;
            }

            System.out.println("Sexo atual: " + sexo);
            System.out.print("Novo sexo (m/f/o): ");
            String novoSexo = scanner.nextLine();
            if (!novoSexo.isEmpty()) {
                sexo = novoSexo;
            }

            System.out.println("Idade atual: " + idade);
            System.out.print("Nova idade: ");
            int novaIdade = scanner.nextInt();
            scanner.nextLine(); // Consumir o caractere de nova linha
            if (novaIdade != 0) {
                idade = novaIdade;
            }

            System.out.println("Cargo atual: " + cargo);
            System.out.print("Novo cargo (vendedor/gerente/CEO): ");
            String novoCargo = scanner.nextLine();
            if (!novoCargo.isEmpty()) {
                cargo = novoCargo;
            }

            System.out.println("Salário atual: " + salario);
            System.out.print("Novo salário: ");
            double novoSalario = scanner.nextDouble();
            scanner.nextLine(); // Consumir o caractere de nova linha
            if (novoSalario != 0) {
                salario = novoSalario;
            }

            System.out.println("Nascimento atual: " + nascimento);
            System.out.print("Nova data de nascimento (AAAA-MM-DD): ");
            String novoNascimento = scanner.nextLine();
            if (!novoNascimento.isEmpty()) {
                nascimento = Date.valueOf(novoNascimento);
            }

            statement = connection.prepareStatement("UPDATE funcionario SET nome = ?, sexo = ?, idade = ?, cargo = ?, salario = ?, nascimento = ? WHERE id = ?");
            statement.setString(1, nome);
            statement.setString(2, sexo);
            statement.setInt(3, idade);
            statement.setString(4, cargo);
            statement.setDouble(5, salario);
            statement.setDate(6, nascimento);
            statement.setInt(7, id);
            statement.executeUpdate();
            statement.close();

            System.out.println("Funcionário atualizado com sucesso!");
        } else {
            System.out.println("Funcionário não encontrado.");
        }
        resultSet.close();
        statement.close();
    }

    // Excluir Funcionário
    private static void excluirFuncionario(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("\n--- Excluir Funcionário ---");
        System.out.print("Digite o ID do funcionário: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir o caractere de nova linha

        PreparedStatement statement = connection.prepareStatement("DELETE FROM funcionario WHERE id = ?");
        statement.setInt(1, id);
        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Funcionário excluído com sucesso!");
        } else {
            System.out.println("Funcionário não encontrado.");
        }
        statement.close();
    }

    // Reajuste Salarial
    private static void reajusteSalarial(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("\n--- Reajuste Salarial ---");
        System.out.print("Digite o reajuste em percentual (ex: 5 para 5%): ");
        double reajustePercentual = scanner.nextDouble();
        scanner.nextLine(); // Consumir o caractere de nova linha
        System.out.print("Digite a categoria do funcionário (vendedor/gerente/CEO): ");
        String categoria = scanner.nextLine();

        PreparedStatement statement = connection.prepareStatement("UPDATE funcionario SET salario = salario * (1 + (? / 100)) WHERE cargo = ?");
        statement.setDouble(1, reajustePercentual);
        statement.setString(2, categoria);
        statement.executeUpdate();
        statement.close();

        System.out.println("Reajuste salarial aplicado com sucesso!");
    }

    // Gerenciar Vendas
    private static void gerenciarVendas(Connection connection, Scanner scanner) throws SQLException {
        while (true) {
            System.out.println("\n--- Gerenciar Vendas ---");
            System.out.println("1. Cadastrar Venda");
            System.out.println("2. Buscar Vendas");
            System.out.println("3. Realizar Sorteio");
            System.out.println("0. Voltar");
            System.out.print("Digite a opção desejada: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir o caractere de nova linha

            switch (opcao) {
                case 1:
                    cadastrarVenda(connection, scanner);
                    break;
                case 2:
                    consultarVendas(connection, scanner);
                    break;
                case 3:
                    realizarSorteio(connection);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    // Cadastrar Venda
    private static void cadastrarVenda(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("\n--- Cadastrar Venda ---");
        System.out.print("ID do vendedor: ");
        int idVendedor = scanner.nextInt();
        scanner.nextLine(); // Consumir o caractere de nova linha
        System.out.print("ID do cliente: ");
        int idCliente = scanner.nextInt();
        scanner.nextLine(); // Consumir o caractere de nova linha
        System.out.print("Data da venda (AAAA-MM-DD): ");
        String dataVenda = scanner.nextLine();

        PreparedStatement statement = connection.prepareStatement("INSERT INTO venda (id_vendedor, id_cliente, data) VALUES (?, ?, ?)");
        statement.setInt(1, idVendedor);
        statement.setInt(2, idCliente);
        statement.setDate(3, Date.valueOf(dataVenda));
        statement.executeUpdate();
        statement.close();

        System.out.println("Venda cadastrada com sucesso!");
    }

    // Consultar Vendas
    private static void consultarVendas(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("\n--- Consultar Vendas ---");
        System.out.print("Digite o ID da venda (ou deixe em branco para buscar todas): ");
        int idVenda = scanner.nextInt();
        scanner.nextLine(); // Consumir o caractere de nova linha

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM venda WHERE id = ?");
        if (idVenda == 0) {
            statement = connection.prepareStatement("SELECT * FROM venda");
        } else {
            statement.setInt(1, idVenda);
        }
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            do {
                int id = resultSet.getInt("id");
                int idVendedor = resultSet.getInt("id_vendedor");
                int idCliente = resultSet.getInt("id_cliente");
                Date data = resultSet.getDate("data");

                System.out.println("ID da venda: " + id);
                System.out.println("ID do vendedor: " + idVendedor);
                System.out.println("ID do cliente: " + idCliente);
                System.out.println("Data da venda: " + data);
                System.out.println("-------------------");
            } while (resultSet.next());
        } else {
            System.out.println("Nenhuma venda encontrada.");
        }
        resultSet.close();
        statement.close();
    }

    // Realizar Sorteio
    private static void realizarSorteio(Connection connection) throws SQLException {
        System.out.println("\n--- Realizar Sorteio ---");

        PreparedStatement statement = connection.prepareStatement("SELECT id FROM cliente ORDER BY RAND() LIMIT 1");
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            int idClienteSorteado = resultSet.getInt("id");

            PreparedStatement statement2 = connection.prepareStatement("SELECT * FROM clienteespecial WHERE id_cliente = ?");
            statement2.setInt(1, idClienteSorteado);
            ResultSet resultSet2 = statement2.executeQuery();

            if (resultSet2.next()) {
                System.out.println("Cliente sorteado (cliente especial): " + resultSet2.getString("nome"));
                System.out.println("Voucher de R$ 200,00");
            } else {
                System.out.println("Cliente sorteado: " + resultSet.getString("nome"));
                System.out.println("Voucher de R$ 100,00");
            }
            resultSet2.close();
            statement2.close();
        } else {
            System.out.println("Nenhum cliente encontrado para sorteio.");
        }
        resultSet.close();
        statement.close();
    }

    // Buscar Produtos
    private static void buscarProdutos(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("\n--- Buscar Produtos ---");
        System.out.print("Digite o nome do produto (ou deixe em branco para buscar todos): ");
        String nome = scanner.nextLine();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM produto WHERE nome LIKE ?");
        statement.setString(1, "%" + nome + "%");
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            do {
                int id = resultSet.getInt("id");
                String nomeProduto = resultSet.getString("nome");
                String descricao = resultSet.getString("descricao");
                int quantidade = resultSet.getInt("quantidade");
                double valor = resultSet.getDouble("valor");

                System.out.println("ID: " + id);
                System.out.println("Nome: " + nomeProduto);
                System.out.println("Descrição: " + descricao);
                System.out.println("Quantidade: " + quantidade);
                System.out.println("Valor: " + valor);
                System.out.println("-------------------");
            } while (resultSet.next());
        } else {
            System.out.println("Nenhum produto encontrado.");
        }
        resultSet.close();
        statement.close();
    }

    // Buscar Clientes
    private static void buscarClientes(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("\n--- Buscar Clientes ---");
        System.out.print("Digite o nome do cliente (ou deixe em branco para buscar todos): ");
        String nome = scanner.nextLine();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM cliente WHERE nome LIKE ?");
        statement.setString(1, "%" + nome + "%");
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            do {
                int id = resultSet.getInt("id");
                String nomeCliente = resultSet.getString("nome");
                String sexo = resultSet.getString("sexo");
                int idade = resultSet.getInt("idade");
                Date nascimento = resultSet.getDate("nascimento");

                System.out.println("ID: " + id);
                System.out.println("Nome: " + nomeCliente);
                System.out.println("Sexo: " + sexo);
                System.out.println("Idade: " + idade);
                System.out.println("Nascimento: " + nascimento);
                System.out.println("-------------------");
            } while (resultSet.next());
        } else {
            System.out.println("Nenhum cliente encontrado.");
        }
        resultSet.close();
        statement.close();
    }

    // Editar Produto
    private static void editarProduto(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("\n--- Editar Produto ---");
        System.out.print("Digite o ID do produto: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir o caractere de nova linha

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM produto WHERE id = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String nome = resultSet.getString("nome");
            String descricao = resultSet.getString("descricao");
            int quantidade = resultSet.getInt("quantidade");
            double valor = resultSet.getDouble("valor");

            System.out.println("Nome atual: " + nome);
            System.out.print("Novo nome: ");
            String novoNome = scanner.nextLine();
            if (!novoNome.isEmpty()) {
                nome = novoNome;
            }

            System.out.println("Descrição atual: " + descricao);
            System.out.print("Nova descrição: ");
            String novaDescricao = scanner.nextLine();
            if (!novaDescricao.isEmpty()) {
                descricao = novaDescricao;
            }

            System.out.println("Quantidade atual: " + quantidade);
            System.out.print("Nova quantidade: ");
            int novaQuantidade = scanner.nextInt();
            scanner.nextLine(); // Consumir o caractere de nova linha
            if (novaQuantidade != 0) {
                quantidade = novaQuantidade;
            }

            System.out.println("Valor atual: " + valor);
            System.out.print("Novo valor: ");
            double novoValor = scanner.nextDouble();
            scanner.nextLine(); // Consumir o caractere de nova linha
            if (novoValor != 0) {
                valor = novoValor;
            }

            statement = connection.prepareStatement("UPDATE produto SET nome = ?, descricao = ?, quantidade = ?, valor = ? WHERE id = ?");
            statement.setString(1, nome);
            statement.setString(2, descricao);
            statement.setInt(3, quantidade);
            statement.setDouble(4, valor);
            statement.setInt(5, id);
            statement.executeUpdate();
            statement.close();

            System.out.println("Produto atualizado com sucesso!");
        } else {
            System.out.println("Produto não encontrado.");
        }
        resultSet.close();
        statement.close();
    }

    // Editar Cliente
    private static void editarCliente(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("\n--- Editar Cliente ---");
        System.out.print("Digite o ID do cliente: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir o caractere de nova linha

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM cliente WHERE id = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String nome = resultSet.getString("nome");
            String sexo = resultSet.getString("sexo");
            int idade = resultSet.getInt("idade");
            Date nascimento = resultSet.getDate("nascimento");

            System.out.println("Nome atual: " + nome);
            System.out.print("Novo nome: ");
            String novoNome = scanner.nextLine();
            if (!novoNome.isEmpty()) {
                nome = novoNome;
            }

            System.out.println("Sexo atual: " + sexo);
            System.out.print("Novo sexo (m/f/o): ");
            String novoSexo = scanner.nextLine();
            if (!novoSexo.isEmpty()) {
                sexo = novoSexo;
            }

            System.out.println("Idade atual: " + idade);
            System.out.print("Nova idade: ");
            int novaIdade = scanner.nextInt();
            scanner.nextLine(); // Consumir o caractere de nova linha
            if (novaIdade != 0) {
                idade = novaIdade;
            }

            System.out.println("Nascimento atual: " + nascimento);
            System.out.print("Nova data de nascimento (AAAA-MM-DD): ");
            String novoNascimento = scanner.nextLine();
            if (!novoNascimento.isEmpty()) {
                nascimento = Date.valueOf(novoNascimento);
            }

            statement = connection.prepareStatement("UPDATE cliente SET nome = ?, sexo = ?, idade = ?, nascimento = ? WHERE id = ?");
            statement.setString(1, nome);
            statement.setString(2, sexo);
            statement.setInt(3, idade);
            statement.setDate(4, nascimento);
            statement.setInt(5, id);
            statement.executeUpdate();
            statement.close();

            System.out.println("Cliente atualizado com sucesso!");
        } else {
            System.out.println("Cliente não encontrado.");
        }
        resultSet.close();
        statement.close();
    }

    // Excluir Produto
    private static void excluirProduto(Connection connection, Scanner scanner)
            throws SQLException {
        System.out.println("\n--- Excluir Produto ---");
        System.out.print("Digite o ID do produto: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir o caractere de nova linha

        PreparedStatement statement = connection.prepareStatement("DELETE FROM produto WHERE id = ?");
        statement.setInt(1, id);
        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Produto excluído com sucesso!");
        } else {
            System.out.println("Produto não encontrado.");
        }
        statement.close();
    }
    // Excluir Cliente
    private static void excluirCliente(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("\n--- Excluir Cliente ---");
        System.out.print("Digite o ID do cliente: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir o caractere de nova linha

        PreparedStatement statement = connection.prepareStatement("DELETE FROM cliente WHERE id = ?");
        statement.setInt(1, id);
        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Cliente excluído com sucesso!");
        } else {
            System.out.println("Cliente não encontrado.");
        }
        statement.close();
    }

    // Excluir Funcionário
    private static void excluirFuncionario(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("\n--- Excluir Funcionário ---");
        System.out.print("Digite o ID do funcionário: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir o caractere de nova linha

        PreparedStatement statement = connection.prepareStatement("DELETE FROM funcionario WHERE id = ?");
        statement.setInt(1, id);
        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Funcionário excluído com sucesso!");
        } else {
            System.out.println("Funcionário não encontrado.");
        }
        statement.close();
    }

    // Visualizar Estatísticas
    private static void visualizarEstatisticas(Connection connection) throws SQLException {
        System.out.println("\n--- Estatísticas de Vendas ---");

        // 1. Produto mais vendido
        PreparedStatement statement = connection.prepareStatement("SELECT p.nome, SUM(v.id) AS total_vendas FROM venda v JOIN produto p ON v.id = p.id GROUP BY p.id ORDER BY total_vendas DESC LIMIT 1");
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            String produtoMaisVendido = resultSet.getString("nome");
            System.out.println("Produto mais vendido: " + produtoMaisVendido);
        }
        resultSet.close();
        statement.close();

        // 2. Vendedor associado ao produto mais vendido
        statement = connection.prepareStatement("SELECT f.nome FROM venda v JOIN produto p ON v.id = p.id JOIN funcionario f ON v.id_vendedor = f.id WHERE p.nome = ? GROUP BY f.id ORDER BY COUNT(v.id) DESC LIMIT 1");
        statement.setString(1, produtoMaisVendido);
        resultSet = statement.executeQuery();
        if (resultSet.next()) {
            String vendedorMaisVendido = resultSet.getString("nome");
            System.out.println("Vendedor associado: " + vendedorMaisVendido);
        }
        resultSet.close();
        statement.close();

        // 3. Produto menos vendido
        statement = connection.prepareStatement("SELECT p.nome, SUM(v.id) AS total_vendas FROM venda v JOIN produto p ON v.id = p.id GROUP BY p.id ORDER BY total_vendas ASC LIMIT 1");
        resultSet = statement.executeQuery();
        if (resultSet.next()) {
            String produtoMenosVendido = resultSet.getString("nome");
            System.out.println("Produto menos vendido: " + produtoMenosVendido);
        }
        resultSet.close();
        statement.close();

        // 4. Valor ganho com o produto mais vendido
        statement = connection.prepareStatement("SELECT SUM(p.valor) AS valor_total FROM venda v JOIN produto p ON v.id = p.id WHERE p.nome = ?");
        statement.setString(1, produtoMaisVendido);
        resultSet = statement.executeQuery();
        if (resultSet.next()) {
            double valorTotalMaisVendido = resultSet.getDouble("valor_total");
            System.out.println("Valor total do produto mais vendido: R$ " + valorTotalMaisVendido);
        }
        resultSet.close();
        statement.close();

        // 5. Mês de maior vendas e mês de menor vendas do produto mais vendido
        statement = connection.prepareStatement("SELECT MONTH(v.data) AS mes, SUM(v.id) AS total_vendas FROM venda v JOIN produto p ON v.id = p.id WHERE p.nome = ? GROUP BY mes ORDER BY total_vendas DESC LIMIT 1");
        statement.setString(1, produtoMaisVendido);
        resultSet = statement.executeQuery();
        if (resultSet.next()) {
            int mesMaiorVenda = resultSet.getInt("mes");
            System.out.println("Mês de maior venda do produto mais vendido: " + mesMaiorVenda);
        }
        resultSet.close();
        statement.close();

        statement = connection.prepareStatement("SELECT MONTH(v.data) AS mes, SUM(v.id) AS total_vendas FROM venda v JOIN produto p ON v.id = p.id WHERE p.nome = ? GROUP BY mes ORDER BY total_vendas ASC LIMIT 1");
        statement.setString(1, produtoMaisVendido);
        resultSet = statement.executeQuery();
        if (resultSet.next()) {
            int mesMenorVenda = resultSet.getInt("mes");
            System.out.println("Mês de menor venda do produto mais vendido: " + mesMenorVenda);
        }
        resultSet.close();
        statement.close();

        // 6. Valor ganho com o produto menos vendido
        statement = connection.prepareStatement("SELECT SUM(p.valor) AS valor_total FROM venda v JOIN produto p ON v.id = p.id WHERE p.nome = ?");
        statement.setString(1, produtoMenosVendido);
        resultSet = statement.executeQuery();
        if (resultSet.next()) {
            double valorTotalMenosVendido = resultSet.getDouble("valor_total");
            System.out.println("Valor total do produto menos vendido: R$ " + valorTotalMenosVendido);
        }
        resultSet.close();
        statement.close();

        // 7. Mês de maior vendas e mês de menor vendas do produto menos vendido
        statement = connection.prepareStatement("SELECT MONTH(v.data) AS mes, SUM(v.id) AS total_vendas FROM venda v JOIN produto p ON v.id = p.id WHERE p.nome = ? GROUP BY mes ORDER BY total_vendas DESC LIMIT 1");
        statement.setString(1, produtoMenosVendido);
        resultSet = statement.executeQuery();
        if (resultSet.next()) {
            int mesMaiorVendaMenosVendido = resultSet.getInt("mes");
            System.out.println("Mês de maior venda do produto menos vendido: " + mesMaiorVendaMenosVendido);
        }
        resultSet.close();
        statement.close();

        statement = connection.prepareStatement("SELECT MONTH(v.data) AS mes, SUM(v.id) AS total_vendas FROM venda v JOIN produto p ON v.id = p.id WHERE p.nome = ? GROUP BY mes ORDER BY total_vendas ASC LIMIT 1");
        statement.setString(1, produtoMenosVendido);
        resultSet = statement.executeQuery();
        if (resultSet.next()) {
            int mesMenorVendaMenosVendido = resultSet.getInt("mes");
            System.out.println("Mês de menor venda do produto menos vendido: " + mesMenorVendaMenosVendido);
        }
        resultSet.close();
        statement.close();
    }

    // Buscar Vendas
    private static void buscarVendas(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("\n--- Buscar Vendas ---");
        System.out.print("Digite o ID da venda (ou deixe em branco para buscar todas): ");
        int idVenda = scanner.nextInt();
        scanner.nextLine(); // Consumir o caractere de nova linha

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM venda WHERE id = ?");
        if (idVenda == 0) {
            statement = connection.prepareStatement("SELECT * FROM venda");
        } else {
            statement.setInt(1, idVenda);
        }
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            do {
                int id = resultSet.getInt("id");
                int idVendedor = resultSet.getInt("id_vendedor");
                int idCliente = resultSet.getInt("id_cliente");
                Date data = resultSet.getDate("data");

                System.out.println("ID da venda: " + id);
                System.out.println("ID do vendedor: " + idVendedor);
                System.out.println("ID do cliente: " + idCliente);
                System.out.println("Data da venda: " + data);
                System.out.println("-------------------");
            } while (resultSet.next());
        } else {
            System.out.println("Nenhuma venda encontrada.");
        }
        resultSet.close();
        statement.close();
    }
}