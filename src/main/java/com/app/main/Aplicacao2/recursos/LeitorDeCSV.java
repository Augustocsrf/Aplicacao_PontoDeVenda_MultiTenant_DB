package com.app.main.Aplicacao2.recursos;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.app.main.Aplicacao2.multitenancy.TenantContext;

public class LeitorDeCSV {

	static PontoDeVenda pdv = null;
	
	
	
	public static List<PontoDeVenda> lerArquivo (String arquivo){
		
		List<PontoDeVenda> list = null;
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader (new FileInputStream(arquivo), StandardCharsets.ISO_8859_1))) {
			
			String linha;
			list = new ArrayList<PontoDeVenda>();
			
			while ((linha = br.readLine()) != null) {
				
				String[] valores = linha.split(",");
				
				valores = ajustarTamanhoDoVetor(valores);
				//Garantir que os valores pra "Cidade" e "Nome" contem algo, se n�o, alterar para "NOME DESCONHECIDO"
				//Garantir que o valor para "CEP" condiza com o formato de 8 numeros, se n�o mudar para "00000000"
				for(int i = 0; i < valores.length; i++){
					switch(i){
					case 0: case 1:
						if(valores[i].equals("")) valores[i] = "NOME DESCONHECIDO";
					break;
					
					case 3:
						if(valores[i].length() != 8) valores[i] = "00000000";
					break;
						
					default:
						if(valores[i].equals(null)) valores[i] = "";
					break;
					
					}
				}
				
				pdv = new PontoDeVenda(valores[0], valores[1], valores[2], valores[3]);
				pdv.setTenantId(TenantContext.getCurrentTenant());
				
				list.add(pdv);
			}
	
		} catch (Exception e) {
			System.err.println(e);
		}
		
		return list;
	}
	
	public static String[] ajustarTamanhoDoVetor (String[] valores){
		//Dar valores pra String quando ela estiver incompleta
		if(valores.length < 4){
			switch(valores.length){
			case 1:
				valores = new String[]{valores[0], "NOME DESCONHECIDO", "", "00000000"};
				break;
			
			case 2:
				valores = new String[]{valores[0], valores[1], "", "00000000"};
				break;
				
			case 3:
				valores = new String[]{valores[0], valores[0], valores[2], "00000000"};
				break;
			}					
		}
		
		//Organizar os valores separados em uma segunda string 
		if(valores.length == 5){
			valores = new String[] {valores[0] + valores[1], valores[2], valores[3], valores[4]};
		}	
		return valores;
	}
}