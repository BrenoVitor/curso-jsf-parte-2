package br.com.caelum.livraria.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.modelo.Venda;

@ManagedBean
@ViewScoped
public class VendaBean {

	public List<Venda> getVendas(long seed) {
		List<Livro> livros = new DAO<Livro>(Livro.class).listaTodos();
		List<Venda> vendas = new ArrayList<Venda>();

		Random random = new Random(seed);

		for (Livro livro : livros) {
			Integer quantidade = random.nextInt(500);
			vendas.add(new Venda(livro, quantidade));
		}

		return vendas;
	}

	public BarChartModel getVendasModel() {
		BarChartModel barChartModel = new BarChartModel();

		ChartSeries vendas2016Series = new ChartSeries();
		vendas2016Series.setLabel("Vendas 2016");

		List<Venda> vendas = getVendas(System.currentTimeMillis());

		for (Venda venda : vendas) {
			vendas2016Series.set(venda.getLivro().getTitulo(), venda.getQuantidade());
		}

		barChartModel.addSeries(vendas2016Series);

		ChartSeries vendas2015Series = new ChartSeries();
		vendas2015Series.setLabel("Vendas 2015");

		vendas = getVendas(System.currentTimeMillis());

		for (Venda venda : vendas) {
			vendas2015Series.set(venda.getLivro().getTitulo(), venda.getQuantidade());
		}

		barChartModel.addSeries(vendas2015Series);

		return barChartModel;
	}

}
