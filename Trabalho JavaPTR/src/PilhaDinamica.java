public class PilhaDinamica implements IEstruturaDinamica {
    private No topo;
    private No base;
    private int quantidade;

    public PilhaDinamica() {
        this.topo = null;
        this.base = null;
        this.quantidade = 0;
    }

    @Override
    public void inserirElemento(Object elemento) {
        No novoNo = new No(elemento);

        if (estaVazia()) {
            topo = novoNo;
            base = novoNo;
        } else {
            novoNo.setAnterior(topo);
            topo.setProx(novoNo);
            topo = novoNo;
        }


        quantidade++;
    }

    @Override
    public void inserirSequencia(Object elementos) {
        Object[] lista = (Object[]) elementos;
        for (Object obj : lista) {
            inserirElemento(obj);
        }
    }


    @Override
    public boolean removerElemento(Object elemento) {
        No atual = topo;

        while (atual != null) {
            if (atual.getConteudo().equals(elemento)) {
                No anterior = atual.getAnterior();
                No proximo = atual.getProx();

                if (anterior != null) {
                    anterior.setProx(proximo);
                } else {
                    base = proximo;
                }

                if (proximo != null) {
                    proximo.setAnterior(anterior);
                } else {
                    topo = anterior;
                }

                quantidade--;
                return true;
            }
            atual = atual.getAnterior();
        }

        return false;
    }


    @Override
    public void removerSequencia(Object elementos) {
        Object[] lista = (Object[]) elementos;
        for (Object obj : lista) {
            removerElemento(obj);
        }

    }

    @Override
    public void removerTodasOcorrencias(Object elemento) {
        No atual = topo;

        while (atual != null) {
            No anterior = atual.getAnterior();
            No proximo = atual.getProx();

            if (atual.getConteudo().equals(elemento)) {
                if (anterior != null) {
                    anterior.setProx(proximo);
                } else {

                    topo = proximo;
                }

                if (proximo != null) {
                    proximo.setAnterior(anterior);
                }

                quantidade--;
            }

            atual = anterior;
        }
    }


    @Override
    public boolean estaCheia() {
        return false;
    }

    @Override
    public boolean estaVazia() {
        return quantidade == 0;
    }

    @Override
    public boolean buscarElemento(Object elemento) {
        No atual = base;

        while (atual != null) {
            if (atual.getConteudo().equals(elemento)) {
                return true;
            }
            atual = atual.getProx();
        }

        return false;
    }

    @Override
    public void ordenarCrescente() {
        if (quantidade < 2) return;

        for (No i = base; i != null; i = i.getProx()) {
            for (No j = i.getProx(); j != null; j = j.getProx()) {
                Comparable a = (Comparable) i.getConteudo();
                Comparable b = (Comparable) j.getConteudo();

                if (a.compareTo(b) > 0) {
                    Object temp = i.getConteudo();
                    i.setConteudo(j.getConteudo());
                    j.setConteudo(temp);
                }
            }
        }
    }

    @Override
    public void ordenarDecrescente() {
        if (quantidade < 2) return;

        for (No i = base; i != null; i = i.getProx()) {
            for (No j = i.getProx(); j != null; j = j.getProx()) {
                Comparable a = (Comparable) i.getConteudo();
                Comparable b = (Comparable) j.getConteudo();

                if (a.compareTo(b) < 0) {
                    Object temp = i.getConteudo();
                    i.setConteudo(j.getConteudo());
                    j.setConteudo(temp);
                }
            }
        }
    }

    @Override
    public int quantidadeElementos() {
        return quantidade;
    }

    @Override
    public void editarElemento(Object antigo, Object novo) {
        No atual = base;

        while (atual != null) {
            if (atual.getConteudo().equals(antigo)) {
                atual.setConteudo(novo);
                return;
            }
            atual = atual.getProx();
        }
    }

    @Override
    public void limpar() {
        topo = null;
        base = null;
        quantidade = 0;
    }

    @Override
    public void exibir() {
        System.out.println("Conteúdo da pilha:");
        No atual = base;
        while (atual != null) {
            System.out.println("-> " + atual.getConteudo());
            atual = atual.getProx();
        }
    }

    @Override
    public No obterPrimeiroElemento() {
        return base;
    }

    @Override
    public No obterUltimoElemento() {
        return topo;
    }
}
