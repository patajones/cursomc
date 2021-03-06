package br.com.patajones.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.patajones.cursomc.domain.Categoria;
import br.com.patajones.cursomc.domain.Cidade;
import br.com.patajones.cursomc.domain.Cliente;
import br.com.patajones.cursomc.domain.Endereco;
import br.com.patajones.cursomc.domain.Estado;
import br.com.patajones.cursomc.domain.ItemPedido;
import br.com.patajones.cursomc.domain.Pagamento;
import br.com.patajones.cursomc.domain.PagamentoComBoleto;
import br.com.patajones.cursomc.domain.PagamentoComCartao;
import br.com.patajones.cursomc.domain.Pedido;
import br.com.patajones.cursomc.domain.Produto;
import br.com.patajones.cursomc.domain.enums.EstadoPagamento;
import br.com.patajones.cursomc.domain.enums.Perfil;
import br.com.patajones.cursomc.domain.enums.TipoCliente;
import br.com.patajones.cursomc.repositories.CategoriaRepository;
import br.com.patajones.cursomc.repositories.CidadeRepository;
import br.com.patajones.cursomc.repositories.ClienteRepository;
import br.com.patajones.cursomc.repositories.EnderecoRepository;
import br.com.patajones.cursomc.repositories.EstadoRepository;
import br.com.patajones.cursomc.repositories.ItemPedidoRepository;
import br.com.patajones.cursomc.repositories.PagamentoRepository;
import br.com.patajones.cursomc.repositories.PedidoRepository;
import br.com.patajones.cursomc.repositories.ProdutoRepository;

@Service
public class DBService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public void instantiateTestDatabase() throws ParseException {
		Categoria cat1 = new Categoria(null, "Informática");		
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama, mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");

		Produto p1 = new Produto(null, "Computador", 1000.0);
		Produto p2 = new Produto(null, "Impressora", 800.0);
		Produto p3 = new Produto(null, "Mouse", 80.0);
		Produto p4 = new Produto(null, "Mesa de Escritório", 300.0);
		Produto p5 = new Produto(null, "Toalha", 50.0);
		Produto p6 = new Produto(null, "Colcha", 200.0);
		Produto p7 = new Produto(null, "TV True Color", 1200.0);
		Produto p8 = new Produto(null, "Roçadeira", 800.0);
		Produto p9 = new Produto(null, "Abajour", 100.0);
		Produto p10 = new Produto(null, "Pendente", 180.0);
		Produto p11 = new Produto(null, "Shampoo", 90.0);

		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5,p6));
		cat4.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));
		
		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));
		
		categoriaRepository.save(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.save(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11));		

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.save(Arrays.asList(est1, est2));
		cidadeRepository.save(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "ricardo.bernardes@gmail.com", "11111111111", encoder.encode("123"), TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("5555555","4444444"));
		
		Cliente cli2 = new Cliente(null, "Anna Costa", "ricardo.bernardes+1@gmail.com", "65913291530", encoder.encode("123"), TipoCliente.PESSOAFISICA);
		cli2.getTelefones().addAll(Arrays.asList("66666666","7777777"));
		cli2.addPerfil(Perfil.ADMIN);

		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38000000", cli1, c1);	
		Endereco e2 = new Endereco(null, "Av Matos", "105", "Sala 203", "Centro", "15000000", cli1, c2);
		Endereco e3 = new Endereco(null, "Av Floriano", "105", null, "Centro", "23400000", cli2, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		cli2.getEnderecos().addAll(Arrays.asList(e3));
		
		clienteRepository.save(Arrays.asList(cli1,cli2));
		enderecoRepository.save(Arrays.asList(e1,e2,e3));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:30"), cli1, e2);
		
		Pagamento pagmto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagmto1);
		
		Pagamento pagmto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagmto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.save(Arrays.asList(ped1, ped2));
		pagamentoRepository.save(Arrays.asList(pagmto1, pagmto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.0, 1, 2000.0);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.0, 2, 80.0);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.0, 1, 800.0);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		itemPedidoRepository.save(Arrays.asList(ip1, ip2, ip3));

	}
}
