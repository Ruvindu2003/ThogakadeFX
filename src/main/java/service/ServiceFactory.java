package service;

import service.custome.impl.CustomerServiceimpl;
import Util.Service_Type;

public class ServiceFactory {

    public static ServiceFactory instance;


    public static ServiceFactory getInstance() {
        if (instance==null){
            return instance=new ServiceFactory();
        }
        return instance;

    }
    public <T extends SuperService>T getServiceType(Service_Type serviceType){

        switch (serviceType){
            case Customer:return (T) CustomerServiceimpl.getInstance();

        }

        return null;
    }


}
