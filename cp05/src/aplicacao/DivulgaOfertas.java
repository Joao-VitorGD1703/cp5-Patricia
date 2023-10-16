package aplicacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import arvores.ArvoreJuridico;
import arvores.Arvores;
import cliente.Cliente;

public class DivulgaOfertas {
	/*
	 * NOMES E RM dos alunos que compõem o grupo
	 * João vítor Guimarães Douverny - RM 93076
	 * Abner Aragon - RM 95620
	 * Gabriel Mendes Pupo - RM 95924
	 * Gustavo Campos - RM 93188
	 */
	public static void main(String[] args) {
		Scanner le = new Scanner(System.in);
		Arvores abb = new Arvores();
		ArvoreJuridico abbJ = new ArvoreJuridico();
	
		int opcao, op, numeroConta;
		String nome, cpfCnpj;
		String tipoConta = null;
		double saldo;
		do {
			System.out.println(" 0 - Encerrar o programa");
			System.out.println(" 1 - Inscrição cliente");
			System.out.println(" 2 - Oferta de novo serviço e/ou aplicação");
			System.out.println(" 3 – Entrar no Submenu ");
			opcao = le.nextInt();
			switch (opcao) {
			case 1:
				System.out.print("Digite nome: ");
				nome = le.next();
				System.out.print("Digite cpf: ");
				cpfCnpj = le.next();
				System.out.print("Digite número da conta: ");
				numeroConta = le.nextInt();
				do {
					System.out.print("Digite 1- Pessoa Física 2- Pessoa Jurídica: ");
					op = le.nextInt();
					switch (op) {
					case 1:
						tipoConta = "Física";
						break;
					case 2:
						tipoConta = "Jurídica";
						break;
					default:
						System.out.println("Opção inválida ");
						op = -1;
					}
				} while (op == -1);
				System.out.print("Informe saldo em aplicações R$: ");
				saldo = le.nextDouble();
				
				Cliente cliente = new Cliente(nome, cpfCnpj, numeroConta, tipoConta, saldo, null);
			
				if (tipoConta.equals("Jurídica")) {
				    abbJ.root = abbJ.inserir(abbJ.root, cliente);
				} else {
				    abb.root = abb.inserir(abb.root, cliente);
				}
				
				break;
			case 2:
				System.out.print("Qual tipo de conta a oferta se destina? ");
				do {
					System.out.print("Digite 1- Pessoa Física 2- Pessoa Jurídica: ");
					op = le.nextInt();
					switch (op) {
					case 1:
						tipoConta = "Física";
						break;
					case 2:
						tipoConta = "Jurídica";
						break;
					default:
						System.out.println("Opção inválida ");
						op = -1;
					}
				} while (op == -1);
				System.out.print("Qual o valor de saldo mínimo exigido: R$ ");
				double saldoMinimo = le.nextDouble();

				Cliente[] clientesAprovados = abb.filtrarClientesComSaldoMaiorOuIgual(saldoMinimo);

				for (Cliente clientesaprovados : clientesAprovados) {
					System.out.println(clientesaprovados.toString());
					System.out.println("gostaria de participar de nossa nova oferta de investimento? digite 1 para SIM e 2 para NAO");
					int resp = le.nextInt();
					if(resp == 1) {
					
				        clientesaprovados.setInteresse("interessado");
					}else {
						 clientesaprovados.setInteresse("desinteressado");
						
					}
				    
				}     
				
				for (Cliente clientesaprovados : clientesAprovados) {
					if(clientesaprovados.getInteresse().equals("interessado")) {
						 System.out.println(clientesaprovados.toString());
						
					}
				   
				}              

				
				clientesAprovados = abbJ.filtrarClientesComSaldoMaiorOuIgual(saldoMinimo);

				for (Cliente clientesaprovados : clientesAprovados) {
					System.out.println(clientesaprovados.toString());
					System.out.println("gostaria de participar de nossa nova oferta de investimento? digite 1 para SIM e 2 para NAO");
					int resp = le.nextInt();
					if(resp == 1) {
					
				        clientesaprovados.setInteresse("interessado");
					}else {
						 clientesaprovados.setInteresse("desinteressado");
						
					}
				    
				}     
				
				for (Cliente clientesaprovados : clientesAprovados) {
					if(clientesaprovados.getInteresse().equals("interessado")) {
						 System.out.println(clientesaprovados.toString());
						
					}
				   
				} 

				
				break;
			case 3:
				do {
		            System.out.println("Menu de Consulta:");
		            
		            System.out.println("1) Consultar cliente por CPF ou CNPJ");
		            System.out.println("2) Atualizar saldo do cliente por número da conta");
		            System.out.println("3) Apresentar quantidade de clientes na ABB");
		            System.out.println("4) Apresentar quantidade de clientes com saldo acima de um valor");
		            System.out.println("5) Voltar ao Menu Principal");
		           

		            System.out.print("Escolha uma opção: ");
		            opcao = le.nextInt();

		            switch (opcao) {
		                case 1:
		                	 System.out.print("Digite o número do documento: ");
		 		            String documento = "";
		 		            documento = le.next();
		 		            Cliente clienteEncontrado =null;
		 		            
		 		           clienteEncontrado = abbJ.pesquisaClientePorDocumento(abbJ.root, documento);
		 		          if (clienteEncontrado != null) {
		 		              System.out.println("Cliente encontrado na árvore abbJ:");
		 		              System.out.println(clienteEncontrado.toString());
		 		          }

		                   
		                   
		 		         clienteEncontrado = abb.pesquisaClientePorDocumento(abb.root, documento);
		 		          if (clienteEncontrado != null) {
		 		              System.out.println("Cliente encontrado na árvore abb:");
		 		              System.out.println(clienteEncontrado.toString());
		 		          }
		                    break;
		                case 2:
		                   
		                	System.out.println("Digite o numero da conta que você deseja acessar:");
		                	int numeroDaConta = le.nextInt();
		                	System.out.println("Digite o novo valor da conta");
		                	double novoSaldo = le.nextDouble();

		                	boolean atualizadoABB = abb.atualizarSaldoPorNumeroDaConta(abb.root, numeroDaConta, novoSaldo);
		                	boolean atualizadoABBj = abbJ.atualizarSaldoPorNumeroDaConta(abbJ.root, numeroDaConta, novoSaldo);

		                	if (atualizadoABB) {
		                	    System.out.println("Saldo atualizado na ABB com sucesso.");
		                	} else {
		                	    System.out.println("Número de conta não encontrado na ABB.");
		                	}

		                	if (atualizadoABBj) {
		                	    System.out.println("Saldo atualizado na ABBj com sucesso.");
		                	} else {
		                	    System.out.println("Número de conta não encontrado na ABBj.");
		                	}
		                	
		                	
		                	
		                    break;
		                case 3:
		                
		                	  System.out.println("Arvore binaria Pessoa Juridica5 " + abbJ.contaNos(abbJ.root, 0));
			                  System.out.println("Arvore binaria Pessoa Física " + abb.contaNos(abb.root, 0));
		                    break;
		                case 4:
		                	System.out.println("Digite o valor minimo: ");
		                	double valor = le.nextDouble();
		                   System.out.println("Arvore binaria Pessoa Física " + abb.contaClientesComSaldoAcimaDeValor(abb.root, valor));
		                   System.out.println("Arvore binaria Pessoa Juridica " +  abbJ.contaClientesComSaldoAcimaDeValor(abbJ.root, valor));
		                    break;
		                default:
		                    System.out.println("Opção inválida. Tente novamente.");
		            }
		       } while (opcao != 5);
		    
				break;
			}
		} while (opcao != 0);

		System.out.println("Clientes que não aceitaram ou não estavam adequados para a oferta");
		System.out.println("");
		System.out.println("Arvore binaria Pessoa Física ");
		
		List<Cliente> clientesDesinteressados = abb.filtrarClientesDesinteressados();

		for (Cliente clienteDesinteressado : clientesDesinteressados) {
		    System.out.println(clienteDesinteressado.toString());
		}

		
		System.out.println("");
		System.out.println("Arvore binaria Pessoa Juridica ");
		
		List<Cliente> clientesDesinteressados2 = abbJ.filtrarClientesDesinteressados();

		for (Cliente clienteDesinteressado : clientesDesinteressados2) {
		    System.out.println(clienteDesinteressado.toString());
		}

		
		le.close();
	}
	

}
	
