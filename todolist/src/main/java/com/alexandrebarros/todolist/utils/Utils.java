package com.alexandrebarros.todolist.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

public class Utils {
	/*Esse método é para que as informações sejam alteradas sem que seja 
	necessário reescrever todas as propriedades em caso de atualização
	de propriedades individuais*/
	
	//Quando o método é static não precisa instancia-lo
	//copia tudo que não for nulo
	public static void copyNonNullProperties(Object source, Object target) {
		
		/*Pega tudo de propriedade nula, ele atribui para dentro desse Bean,
		e vai fazer as mesclas das informações*/		
		BeanUtils.copyProperties(source, target, 
				getNullPropertyNames(source));
		}

	//Verifica se os atributos ou propriedades do Objeto são nulos,
	public static String[] getNullPropertyNames(Object source) {
		final BeanWrapper src = new BeanWrapperImpl();
		
		//Gera um Array com todas as propriedades do objeto
		PropertyDescriptor[] pds = src.getPropertyDescriptors();
		
		//Cria um conjunto com as propriedades que tem valores nulos
		Set<String> emptyNames = new HashSet<>();
		
		//For das propriedades
		for(PropertyDescriptor pd: pds) {
			//Obtém o valor atual da propriedade 
			Object srcValue = src.getPropertyValue(pd.getName());
			//Se for o value for igual a nulo coloca dentro do emptyNames
			if(srcValue == null) {
				emptyNames.add(pd.getName());
			}
		}
		
		//Array para armazenar todos os atributos nulos
		String[] result = new String[emptyNames.size()];
		//converte o conjunto de names para um array de Strings
		return emptyNames.toArray(result);
	}
}
