import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

class No {
    int chave;
    No esquerda, direita;

    public No(int item) {
        chave = item;
        esquerda = direita = null;
    }
}

class ArvoreBinaria {
    No raiz;

    ArvoreBinaria() {
        raiz = null;
    }

    void inserir(int chave) {
        raiz = inserirRec(raiz, chave);
    }

    No inserirRec(No raiz, int chave) {
        if (raiz == null) {
            raiz = new No(chave);
            return raiz;
        }
        if (chave < raiz.chave)
            raiz.esquerda = inserirRec(raiz.esquerda, chave);
        else if (chave > raiz.chave)
            raiz.direita = inserirRec(raiz.direita, chave);
        return raiz;
    }

    void deletarChave(int chave) {
        raiz = deletarRec(raiz, chave);
    }

    No deletarRec(No raiz, int chave) {
        if (raiz == null) return raiz;

        if (chave < raiz.chave)
            raiz.esquerda = deletarRec(raiz.esquerda, chave);
        else if (chave > raiz.chave)
            raiz.direita = deletarRec(raiz.direita, chave);
        else {
            if (raiz.esquerda == null)
                return raiz.direita;
            else if (raiz.direita == null)
                return raiz.esquerda;

            raiz.chave = minValor(raiz.direita);
            raiz.direita = deletarRec(raiz.direita, raiz.chave);
        }

        return raiz;
    }

    int minValor(No raiz) {
        int minValor = raiz.chave;
        while (raiz.esquerda != null) {
            minValor = raiz.esquerda.chave;
            raiz = raiz.esquerda;
        }
        return minValor;
    }

    void preOrdem(No no) {
        if (no != null) {
            System.out.print(no.chave + " ");
            preOrdem(no.esquerda);
            preOrdem(no.direita);
        }
    }

    void inOrdem(No no) {
        if (no != null) {
            inOrdem(no.esquerda);
            System.out.print(no.chave + " ");
            inOrdem(no.direita);
        }
    }

    void posOrdem(No no) {
        if (no != null) {
            posOrdem(no.esquerda);
            posOrdem(no.direita);
            System.out.print(no.chave + " ");
        }
    }

    void ordemNivel() {
        if (raiz == null) return;

        Queue<No> fila = new LinkedList<>();
        fila.add(raiz);
        while (!fila.isEmpty()) {
            No noTemp = fila.poll();
            System.out.print(noTemp.chave + " ");

            if (noTemp.esquerda != null) {
                fila.add(noTemp.esquerda);
            }
            if (noTemp.direita != null) {
                fila.add(noTemp.direita);
            }
        }
    }

    void imprimirPreOrdem() {
        preOrdem(raiz);
        System.out.println();
    }

    void imprimirInOrdem() {
        inOrdem(raiz);
        System.out.println();
    }

    void imprimirPosOrdem() {
        posOrdem(raiz);
        System.out.println();
    }

    void imprimirOrdemNivel() {
        ordemNivel();
        System.out.println();
    }

    public static void main(String[] args) {
        ArvoreBinaria arvore = new ArvoreBinaria();
        ArrayList<Integer> numeros = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < 20; i++) {
            numeros.add(rand.nextInt(101));
        }

        System.out.println("Números sorteados: " + numeros);

        for (int num : numeros) {
            arvore.inserir(num);
        }

        System.out.println("Pré-ordem:");
        arvore.imprimirPreOrdem();

        System.out.println("In-ordem:");
        arvore.imprimirInOrdem();

        System.out.println("Pós-ordem:");
        arvore.imprimirPosOrdem();

        System.out.println("Ordem por nível:");
        arvore.imprimirOrdemNivel();

        Collections.shuffle(numeros);

        for (int i = 0; i < 5; i++) {
            arvore.deletarChave(numeros.get(i));
        }

        System.out.println("\nApós deletar 5 elementos:");

        System.out.println("Pré-ordem:");
        arvore.imprimirPreOrdem();

        System.out.println("In-ordem:");
        arvore.imprimirInOrdem();

        System.out.println("Pós-ordem:");
        arvore.imprimirPosOrdem();

        System.out.println("Ordem por nível:");
        arvore.imprimirOrdemNivel();
    }
}
