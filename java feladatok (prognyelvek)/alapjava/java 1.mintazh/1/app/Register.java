package app;

import commerce.company.Company;
import commerce.company.PublicTransportCompany;

import java.util.HashSet;
import java.util.Set;

public class Register{
    public static void main(String[] args){
        Set<Company> companies = new HashSet<>();
        for(String arg: args){
            companies.add(PublicTransportCompany.createFromString(arg));
        }

        System.out.println(companies.size());

        for(Company company: companies){
            System.out.println(company.toString());
        }
    }
}