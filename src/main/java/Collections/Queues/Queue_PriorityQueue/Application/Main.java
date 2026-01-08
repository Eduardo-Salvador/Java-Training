package Collections.Queues.Queue_PriorityQueue.Application;
import Collections.Queues.Queue_PriorityQueue.Domain.Patient;
import Collections.Queues.Queue_PriorityQueue.Services.HospitalService;

public class Main {
    public static void main(String[] args) {
        Patient p1 = new Patient("Carlos Silva", 1, "Infarto agudo do miocárdio com dor torácica intensa");
        Patient p2 = new Patient("Ana Costa", 1, "Parada respiratória - necessita ventilação mecânica");
        Patient p3 = new Patient("Roberto Lima", 1, "AVC isquêmico com perda de consciência");
        Patient p4 = new Patient("Maria Santos", 2, "Fratura exposta na perna direita com sangramento");
        Patient p5 = new Patient("João Pereira", 2, "Queimadura de 2º grau em 30% do corpo");
        Patient p6 = new Patient("Fernanda Souza", 2, "Hemorragia digestiva com vômito de sangue");
        Patient p7 = new Patient("Paulo Oliveira", 2, "Trauma craniano por acidente de moto");
        Patient p8 = new Patient("Juliana Alves", 3, "Dor abdominal intensa no quadrante inferior direito");
        Patient p9 = new Patient("Ricardo Martins", 3, "Febre alta 40°C há 2 dias com calafrios");
        Patient p10 = new Patient("Beatriz Rocha", 3, "Crise asmática com dificuldade respiratória moderada");
        Patient p11 = new Patient("Lucas Barbosa", 3, "Entorse grave no tornozelo com inchaço");
        Patient p12 = new Patient("Amanda Ferreira", 4, "Dor de cabeça persistente há 3 dias");
        Patient p13 = new Patient("Gabriel Costa", 4, "Corte superficial no dedo que precisa sutura");
        Patient p14 = new Patient("Patricia Mendes", 4, "Náusea e vômito após refeição suspeita");
        Patient p15 = new Patient("Diego Carvalho", 4, "Dor lombar crônica com piora nos últimos dias");
        Patient p16 = new Patient("Camila Rodrigues", 5, "Resfriado comum com congestão nasal leve");
        Patient p17 = new Patient("Thiago Nascimento", 5, "Renovação de receita para medicamento de uso contínuo");
        Patient p18 = new Patient("Larissa Campos", 5, "Check-up preventivo anual de rotina");
        Patient p19 = new Patient("Bruno Dias", 5, "Solicitação de atestado para retorno ao trabalho");
        Patient p20 = new Patient("Mariana Teixeira", 5, "Buscar resultado de exames laboratoriais");

        HospitalService hospital = new HospitalService();
        hospital.add(p1);
        hospital.add(p4);
        hospital.add(p8);
        hospital.add(p6);
        hospital.add(p12);
        hospital.add(p20);
        hospital.add(p7);
        hospital.add(p5);
        hospital.add(p13);
        hospital.add(p19);
        hospital.add(p15);
        hospital.add(p10);
        hospital.add(p11);
        hospital.add(p16);
        hospital.add(p9);
        hospital.add(p17);
        hospital.add(p14);
        hospital.add(p18);
        hospital.add(p3);
        hospital.add(p2);

        hospital.service();
        hospital.service();
        hospital.service();
        hospital.service();
        hospital.service();
        hospital.service();
        hospital.service();
        hospital.service();
        hospital.service();
        hospital.service();
        hospital.service();
        hospital.service();
        hospital.service();
        hospital.service();
        hospital.service();
        hospital.service();
        hospital.service();
        hospital.service();
        hospital.service();
        hospital.service();
        hospital.service();
    }
}