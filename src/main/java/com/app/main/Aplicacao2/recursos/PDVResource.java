package com.app.main.Aplicacao2.recursos;

import com.app.main.Aplicacao2.multitenancy.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PDVResource {

    @Autowired
    PDVService pdvServ;
    
	public static String dataDirectory = System.getProperty("user.dir") + "/data";
    
    @RequestMapping("/all")
    public List<PontoDeVenda> getAll() {
        return pdvServ.findAll();
    }
    
    @RequestMapping("cidade/{cidade}")
    public List<PontoDeVenda> findByCity(@PathVariable final String cidade) {    	
        return pdvServ.findByCidade(cidade);
    }
    
    @RequestMapping("/cep/{cep}")
    public List<PontoDeVenda> findByCep(@PathVariable("cep") final String cep) {
        return pdvServ.findByCep(cep);
    }
    
    @RequestMapping("/nome/{nome}")
    public List<PontoDeVenda> findByName(@PathVariable("nome") final String nome) {
        return pdvServ.findByNome(nome);
    }
    
    @RequestMapping("/tenant/{tenant}")
    public List<PontoDeVenda> findByTenantId(@PathVariable("tenant") final String tid) {
        return pdvServ.findByTenantId(tid);
    }
    
    @RequestMapping("/upload")
    public String uploading(@RequestParam("data") MultipartFile file){    	
    	Path filePath = Paths.get(dataDirectory, file.getOriginalFilename());
    	try {
			file.transferTo(new File(filePath.toString()));
			
			lerArquivoESalvarNoDB(filePath.toString());
			
			return "Uploaded";
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return "Failed to read file. Please read error message for more information.";
		}
    }
    
    public void lerArquivoESalvarNoDB(String filePath){
    	List<PontoDeVenda> list = new ArrayList<PontoDeVenda>();
    	
    	list = LeitorDeCSV.lerArquivo(filePath);
    	
    	for (PontoDeVenda pdv : list){
			pdv = pdvServ.save(pdv);
		}
    }
    
}