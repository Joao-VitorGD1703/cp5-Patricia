package arvores;



import java.util.ArrayList;
import java.util.List;

import cliente.Cliente;

public class ArvoreJuridico {
	
	private class ARVORE {
		Cliente cliente;
		ARVORE esq, dir;
	}

	public ARVORE root = null;

	public ARVORE inserir(ARVORE p, Cliente cliente) {
		if (p == null) {
			p = new ARVORE();
			p.cliente = cliente;
			p.esq = null;
			p.dir = null;
		} else if (cliente.getNumeroConta() < p.cliente.getNumeroConta()) {
			p.esq = inserir(p.esq, cliente);
		} else {
			p.dir = inserir(p.dir, cliente);
		}
		return p;
	}
	
	
	
	
	
	
	

	public boolean consulta(ARVORE p, int numeroConta) {
		if (p == null) {
			return false;
		} else {
			if (numeroConta == p.cliente.getNumeroConta()) {
				return true;
			} else {
				if (numeroConta < p.cliente.getNumeroConta()) {
					return consulta(p.esq, numeroConta);
				} else {
					return consulta(p.dir, numeroConta);
				}
			}
		}
	}
	
	public boolean atualizarSaldoPorNumeroDaConta(ARVORE p, int numeroConta, double novoSaldo) {
	    if (p == null) {
	        return false; 
	    }

	   
	    if (numeroConta == p.cliente.getNumeroConta()) {
	        p.cliente.setSaldo(novoSaldo); 
	        return true; 
	    } else if (numeroConta < p.cliente.getNumeroConta()) {
	    
	        return atualizarSaldoPorNumeroDaConta(p.esq, numeroConta, novoSaldo);
	    } else {
	       
	        return atualizarSaldoPorNumeroDaConta(p.dir, numeroConta, novoSaldo);
	    }
	}


	
	public int contaClientesComSaldoAcimaDeValor(ARVORE p, double valorMinimo) {
	    if (p == null) {
	        return 0;
	    }

	    int cont = 0;

	    double saldoCliente = p.cliente.getSaldo();

	    if (saldoCliente > valorMinimo) {
	        cont++;
	    }

	    int contEsq = contaClientesComSaldoAcimaDeValor(p.esq, valorMinimo);
	    int contDir = contaClientesComSaldoAcimaDeValor(p.dir, valorMinimo);

	    cont += contEsq + contDir;

	    return cont;
	}

	
	
	
	
	
	public int contaConsulta(ARVORE p, int numeroConta, int cont) {
		if (p != null) {
			cont++;
			if (numeroConta == p.cliente.getNumeroConta()) {
				return cont;
			} else {
				if (numeroConta < p.cliente.getNumeroConta()) {
					cont = contaConsulta(p.esq, numeroConta, cont);
				} else {
					cont = contaConsulta(p.dir, numeroConta, cont);
				}
			}
		}
		return cont;
	}
	
	
	
	
	
	
	

	/*public Cliente contaConsultaDocumento(ARVORE p, String cpfCnpj, Cliente foundCliente) {
	    if (p != null) {
	        int comparisonResult = cpfCnpj.compareTo(p.cliente.getDocumentoCpfCnpj());
	        if (comparisonResult == 0) {
	            foundCliente = p.cliente;
	        } else if (comparisonResult < 0) {
	            foundCliente = contaConsultaDocumento(p.esq, cpfCnpj, foundCliente);
	        } else {
	            foundCliente = contaConsultaDocumento(p.dir, cpfCnpj, foundCliente);
	        }
	    }
	    return foundCliente;
	}*/
	
	public Cliente pesquisaClientePorDocumento(ARVORE p, String documento) {
	    if (p != null) {
	        int comparisonResult = documento.compareTo(p.cliente.getDocumentoCpfCnpj());
	        if (comparisonResult == 0) {
	            return p.cliente; // Cliente encontrado
	        } else if (comparisonResult < 0) {
	            return pesquisaClientePorDocumento(p.esq, documento);
	        } else {
	            return pesquisaClientePorDocumento(p.dir, documento);
	        }
	    }
	    return null; // Cliente não encontrado
	}


	
	
	
	
	

	public ARVORE removeValor(ARVORE p, int numeroConta) {
		if (p != null) {
			if (numeroConta == p.cliente.getNumeroConta()) {
				if (p.esq == null && p.dir == null) {
					return null;
				}
				if (p.esq == null) {
					return p.dir;
				} else if (p.dir == null) {
					return p.esq;
				} else {
					ARVORE aux, ref;
					ref = p.dir;
					aux = p.dir;
					while (aux.esq != null)
						aux = aux.esq;
					aux.esq = p.esq;
					return ref;
				}
			} else {
				if (numeroConta < p.cliente.getNumeroConta()) {
					p.esq = removeValor(p.esq, numeroConta);
				} else {
					p.dir = removeValor(p.dir, numeroConta);
				}
			}
		}
		return p;
	}

	
	
	
	
	
	
	public int contaNos(ARVORE p, int cont) {
		if (p != null) {
			cont++;
			cont = contaNos(p.esq, cont);
			cont = contaNos(p.dir, cont);
		}
		return cont;
	}

	public void listaEmOrdem(ARVORE p) {
		if (p != null) {
			listaEmOrdem(p.esq);
			System.out.print(" " + p.cliente.getNumeroConta());
			listaEmOrdem(p.dir);
		}
	}
	
	
	
	

	


	    public Cliente[] filtrarClientesComSaldoMaiorOuIgual(double valorMinimo) {
	        List<Cliente> clientesFiltrados = new ArrayList<>();
	        filtrarClientesComSaldoMaiorOuIgual(root, valorMinimo, clientesFiltrados);
	        
	        Cliente[] clientesArray = clientesFiltrados.toArray(new Cliente[clientesFiltrados.size()]);
	        
	        return clientesArray;
	    }

	    private void filtrarClientesComSaldoMaiorOuIgual(ARVORE p, double valorMinimo, List<Cliente> clientesFiltrados) {
	        if (p != null) {
	            filtrarClientesComSaldoMaiorOuIgual(p.esq, valorMinimo, clientesFiltrados);
	            if (p.cliente.getSaldo() >= valorMinimo) {
	                clientesFiltrados.add(p.cliente);
	            }
	            filtrarClientesComSaldoMaiorOuIgual(p.dir, valorMinimo, clientesFiltrados);
	        }
	    }
	

	    
	    
	    
	    
	    
	    
	    
	    public List<Cliente> filtrarClientesDesinteressados() {
	        List<Cliente> clientesDesinteressados = new ArrayList<>();
	        filtrarClientesDesinteressados(root, clientesDesinteressados);
	        return clientesDesinteressados;
	    }

	    private void filtrarClientesDesinteressados(ARVORE p, List<Cliente> clientesDesinteressados) {
	        if (p != null) {
	            filtrarClientesDesinteressados(p.esq, clientesDesinteressados);
	            if (p.cliente.getInteresse().equals("desinteressado")) {
	                clientesDesinteressados.add(p.cliente);
	            }
	            filtrarClientesDesinteressados(p.dir, clientesDesinteressados);
	        }
	    }

	

	

}
