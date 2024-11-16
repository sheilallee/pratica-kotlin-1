//package br.ifpb.pdm.praticas
//Prática Kotlin: Sintaxe Básica

class Livro(var titulo: String, var preco: Double) {
    override fun toString(): String {
        return "Livro: Título = $titulo, Preço = R$ %.2f".format(preco)
    }
}

fun menu() {
    println("1 - Cadastrar livro")
    println("2 - Excluir livro")
    println("3 - Buscar livro")
    println("4 - Editar livro")
    println("5 - Listar livros")
    println("6 - Listar livros que começam com letra escolhida")
    println("7 - Listar livros com preço abaixo do informado")
    println("8 - Sair")
}

fun inputTitulo(): String {
    print("Digite o titulo do livro: ")
    return readlnOrNull() ?:""
}

fun inputPreco(): Double {
    var preco: Double
    do {
        print("Digite o preco do livro (não pode ser negativo): ")
        preco = readlnOrNull()?.toDoubleOrNull() ?: 0.0
        if (preco < 0) {
            println("Preço inválido. Tente novamente.")
        }
    } while (preco < 0)
    return preco
}

fun cadastrarLivro(repositorio: MutableList<Livro>) {
    val titulo = inputTitulo()
    val preco = inputPreco()

    repositorio.add(Livro(titulo, preco))
    println("\nCadastrado com sucesso!\n")
}

fun excluirLivro(repositorio: MutableList<Livro>) {
    val livro = buscarNome(repositorio)
    // repositorio.remove(livro)
    // println("Livro removido com sucesso!")
    if (livro != null && repositorio.remove(livro)) {
        println("Livro removido com sucesso!")
    } else {
        println("Nenhum livro foi removido.")
    }
}

fun buscarNome(repositorio: MutableList<Livro>): Livro? {
    val titulo = inputTitulo()
    return repositorio.find { it.titulo == titulo }
}

fun editarLivro(repositorio: MutableList<Livro>) {
    val livro = buscarNome(repositorio)

    if (livro != null) {
        println("O que você deseja editar?")
        println("1 - Título")
        println("2 - Preço")
        val opcao = readlnOrNull()?.toIntOrNull() ?: 0

        when (opcao) {
            1 -> {
                print("Digite o novo título: ")
                val novoTitulo = readlnOrNull()?.trim() ?: ""
                if (novoTitulo.isNotEmpty()) {
                    val livroAtualizado = Livro(novoTitulo, livro.preco)
                    repositorio[repositorio.indexOf(livro)] = livroAtualizado
                    println("Título alterado com sucesso!")
                } else {
                    println("Título inválido. Operação cancelada.")
                }
            }
            2 -> {
                val novoPreco = inputPreco()
                val livroAtualizado = Livro(livro.titulo, novoPreco)
                repositorio[repositorio.indexOf(livro)] = livroAtualizado
                println("Preço alterado com sucesso!")
            }
            else -> println("Opção inválida.")
        }
    } else {
        println("Livro não encontrado.")
    }
}

fun listar(repositorio: MutableList<Livro>) {
    // Implemnte seu codigo aqui
    if (repositorio.isEmpty()) {
        println("Nenhum livro cadastrado.")
    } else {
        for (i in repositorio.indices) {
            println(repositorio[i])
        }
    }
}

fun listarComLetraInicial(repositorio: MutableList<Livro>) {
    print("Informe a letra: ")
    var letra = readlnOrNull() ?:""

    while(letra.length > 1) {
        print("Informe apenas uma letra: ")
        letra = readlnOrNull() ?:""
    }

    if(letra != "") {
        val livros = repositorio.filter { it.titulo.startsWith(letra) }
        livros.forEach {println(it)}
    } else {
        println("É necessário informar pelo menos um caracter para esta função executar!")
    }
}

fun listarComPrecoAbaixo(repositorio: MutableList<Livro>) {
    val preco = inputPreco()
    val livros = repositorio.filter { it.preco < preco }
    livros.forEach { println(it) }
}

fun main() {
    val repositorioLivros = mutableListOf<Livro>()
    repositorioLivros.add(Livro("Livro dos Livros", 999999.99))
    repositorioLivros.add(Livro("Turma da Monica", 4.99))
    repositorioLivros.add(Livro("Kotlin for Dummies", 29.99))
    repositorioLivros.add(Livro("A", 59.99))

    var opcao = 0
    while (opcao != 8) {
        menu()
        println(repositorioLivros[0])
        print("Digite a opção: ")
        opcao = readlnOrNull()?.toInt() ?:8

        when (opcao) {
            1 -> cadastrarLivro(repositorioLivros)
            2 -> excluirLivro(repositorioLivros)
            3 -> {
                val livro = buscarNome(repositorioLivros)
                println(livro)
            }
            4 -> editarLivro(repositorioLivros)
            5 -> listar(repositorioLivros)
            6 -> listarComLetraInicial(repositorioLivros)
            7 -> listarComPrecoAbaixo(repositorioLivros)
            8 -> println("Até a próxima :)")
        }
        Thread.sleep(3000)
    }
}




