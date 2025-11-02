package br.edu.ifba.vca.bsi.ed.linkedstackdao.dao;

import br.edu.ifba.vca.bsi.ed.linkedstackdao.dao.repository.Stackable;
import br.edu.ifba.vca.bsi.ed.linkedstackdao.dao.repository.LinkedStack;
import br.edu.ifba.vca.bsi.ed.linkedstackdao.model.Car;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.NoSuchElementException;

public class CarDAOLinkedStack implements CarDAO{
    private Stackable<Car> cars = new LinkedStack<>(20);

    // Operações básicas CRUD
    /**
     * Adiciona um carro na pilha
     *
     * @param car, o carro a ser adicionado
     */
    @Override
    public void addCar(Car car) {
        cars.push(car);
    }

    /**
     * Busca um carro pela sua placa
     *
     * @param plateLicense, placa do carro a ser buscado
     * @return carro encontrado
     */
    @Override
    public Car getCar(String plateLicense) {
        Stackable<Car> tempCars = new LinkedStack<>(20);
        Car resultCar = null;
        
        while (!cars.isEmpty()) {
            Car car = cars.pop();
            tempCars.push(car);
            if (car.getLicensePlate().equalsIgnoreCase(plateLicense)) {
                resultCar = car;
                break;
            }
        }

        while (!tempCars.isEmpty()) {
            cars.push(tempCars.pop());
        }

        return resultCar;
    }

    /**
     * Busca todos os carros da pilha
     *
     * @return array com todos os carros
     */
    @Override
    public Car[] getAllCars() {
        return stackToArray(cars);
    }

    /**
     * Atualiza um carro da pilha
     *
     * @param newCar que atualizará um carro da pilha
     */
    @Override
    public void updateCar(Car newCar) {
        Stackable<Car> tempCars = new LinkedStack<>(20);

        while (!cars.isEmpty()) {
            Car car = cars.pop();
            if (newCar.getLicensePlate().equalsIgnoreCase(car.getLicensePlate())) {
                tempCars.push(newCar);
            } else {
                tempCars.push(car);
            }
        }

        while (!tempCars.isEmpty()) {
            cars.push(tempCars.pop());
        }
    }

    /**
     * Remove um carro da pilha pela sua placa
     *
     * @param plateLicense, placa do carro a ser removido
     * @return carro removido da pilha
     */
    @Override
    public Car deleteCar(String plateLicense) {
        Stackable<Car> tempCars = new LinkedStack<>(20);
        Car resultCar = null;

        while (!cars.isEmpty()) {
            Car car = cars.pop();
            if (car.getLicensePlate().equalsIgnoreCase(plateLicense)) {
                resultCar = car;
                break;
            } else {
                tempCars.push(car);
            }
        }

        while (!tempCars.isEmpty()) {
            cars.push(tempCars.pop());
        }

        return resultCar;
    }

    // Operações de consulta específicas para carros

    /**
     * Busca um carro na pilha pela sua placa
     *
     * @param licensePlate, placa do carro a ser buscado
     * @return carro encontrado
     */
    @Override
    public Car getCarByLicensePlate(String licensePlate) {
        Stackable<Car> tempCars = new LinkedStack<>(20);
        Car resultCar = null;

        while (!cars.isEmpty()) {
            Car car = cars.pop();
            tempCars.push(car);
            if (car.getLicensePlate().equalsIgnoreCase(licensePlate)) {
                resultCar = car;
                break;
            }
        }

        while (!tempCars.isEmpty()) {
            cars.push(tempCars.pop());
        }

        return resultCar;
    }

    /**
     * Busca carros na pilha pela sua marca
     *
     * @param mark, marca dos carros a serem buscados
     * @return array com carros encontrados
     */
    @Override
    public Car[] getCarsByMark(String mark) {
        Stackable<Car> tempCars = new LinkedStack<>(20);
        Stackable<Car> foundCars = new LinkedStack<>(20);

        while (!cars.isEmpty()) {
            Car car = cars.pop();
            tempCars.push(car);
            if (car.getMark().equalsIgnoreCase(mark)) {
                foundCars.push(car);
            }
        }

        while (!tempCars.isEmpty()) {
            cars.push(tempCars.pop());
        }

        return stackToArray(foundCars);
    }

    /**
     * Busca carros na pilha pelo seu modelo
     *
     * @param model, modelo dos carros a serem buscados
     * @return array com os carros encontrados
     */
    @Override
    public Car[] getCarsByModel(String model) {
        Stackable<Car> tempCars = new LinkedStack<>(20);
        Stackable<Car> foundCars = new LinkedStack<>(20);

        while (!cars.isEmpty()) {
            Car car = cars.pop();
            tempCars.push(car);
            if (car.getModel().equalsIgnoreCase(model)) {
                foundCars.push(car);
            }
        }

        while (!tempCars.isEmpty()) {
            cars.push(tempCars.pop());
        }

        return stackToArray(foundCars);
    }

    /**
     * Busca carros na pilha pela sua cor
     *
     * @param color, cor dos carros a serem buscados
     * @return array com carros encontrados
     */
    @Override
    public Car[] getCarsByColor(String color) {
        Stackable<Car> tempCars = new LinkedStack<>(20);
        Stackable<Car> foundCars = new LinkedStack<>(20);

        while (!cars.isEmpty()) {
            Car car = cars.pop();
            tempCars.push(car);
            if (car.getColor() != null && car.getColor().equalsIgnoreCase(color)) {
                foundCars.push(car);
            }
        }

        while (!tempCars.isEmpty()) {
            cars.push(tempCars.pop());
        }

        return stackToArray(foundCars);
    }

    /**
     * Busca carros na pilha pelo seu dono
     *
     * @param owner, dono dos carros a serem buscados
     * @return array com carros encontrados
     */
    @Override
    public Car[] getCarsByOwner(String owner) {
        Stackable<Car> tempCars = new LinkedStack<>(20);
        Stackable<Car> foundCars = new LinkedStack<>(20);

        while (!cars.isEmpty()) {
            Car car = cars.pop();
            tempCars.push(car);
            if (car.getOwnerName() != null && car.getOwnerName().equalsIgnoreCase(owner)) {
                foundCars.push(car);
            }
        }

        while (!tempCars.isEmpty()) {
            cars.push(tempCars.pop());
        }

        return stackToArray(foundCars);
    }

    /**
     * Dado um intervalo de tempo, busca carros dentro do
     * intervaldo pelo tempo de chegada do carro
     *
     * @param initialMoment, data inicial do intervalo
     * @param finalMoment, data final do intervalo
     * @return array com carros encontrados dentro do intervalo
     */
    @Override
    public Car[] getCarsByMomentArrival(LocalDateTime initialMoment, LocalDateTime finalMoment) {
        Stackable<Car> tempCars = new LinkedStack<>(20);
        Stackable<Car> foundCars = new LinkedStack<>(20);

        while (!cars.isEmpty()) {
            Car car = cars.pop();
            tempCars.push(car);
            if (car.getArrived() != null) {
                if (!car.getArrived().isBefore(initialMoment) && !car.getArrived().isAfter(finalMoment)) {
                    foundCars.push(car);
                }
            }
        }

        while (!tempCars.isEmpty()) {
            cars.push(tempCars.pop());
        }

        return stackToArray(foundCars);
    }

    // Operações de análise e estatísticas
    /**
     * Busca o carro com a data de chegada mais recente
     *
     * @return carro encontrado
     */
    @Override
    public Car getCarByNewestArrival() {
        Stackable<Car> tempCars = new LinkedStack<>(20);
        Car resultCar = null;

        while (!cars.isEmpty()) {
            Car car = cars.pop();
            tempCars.push(car);
            if (car.getArrived() != null) {
                resultCar = car;
                break;
            }
        }

        while (!cars.isEmpty()) {
            Car car = cars.pop();
            tempCars.push(car);

            if (car.getArrived() != null && (car.getArrived().isAfter(resultCar.getArrived()))) {
                resultCar = car;
            }
        }

        while (!tempCars.isEmpty()) {
            cars.push(tempCars.pop());
        }

        return resultCar;
    }

    /**
     * Busca o carro com a data de chegada mais antiga
     *
     * @return carro encontrado
     */
    @Override
    public Car getCarByOldestArrival() {
        Stackable<Car> tempCars = new LinkedStack<>(20);
        Car resultCar = null;

        while (!cars.isEmpty()) {
            Car car = cars.pop();
            tempCars.push(car);
            if (car.getArrived() != null) {
                resultCar = car;
                break;
            }
        }

        while (!cars.isEmpty()) {
            Car car = cars.pop();
            tempCars.push(car);

            if (car.getArrived() != null && (car.getArrived().isBefore(resultCar.getArrived()))) {
                resultCar = car;
            }

        }

        while (!tempCars.isEmpty()) {
            cars.push(tempCars.pop());
        }

        return resultCar;
    }

    // Operações de relatório e estatísticas
    /**
     * Imprime todos os carros que estão na pilha
     *
     * @return String representando os carros da pilha
     */
    @Override
    public String printCars() {
        return cars.toString();
    }

    /**
     * Busca a quantidade total de carros presentes na pilha
     *
     * @return int com o número total
     */
    @Override
    public int getTotalCars() {
       return countElements(cars);
    }

    /**
     * Busca a marca mais popular dentre os carros que estão na pilha
     *
     * @return a marca mais popular encontrada
     */
    @Override
    public String getMostPopularMark() {
        Stackable<Car> tempCars = new LinkedStack<>(20);
        String resultMark = null;
        int most = 0;

        if (!cars.isEmpty()) {
            Car car = cars.pop();
            tempCars.push(car);
            most = countMarkOcurrences(car.getMark(), cars);
            resultMark = car.getMark();
        }

        while (!cars.isEmpty()) {
            Car car = cars.pop();
            tempCars.push(car);
            int newMost = countMarkOcurrences(car.getMark(), cars);
            if (newMost > most) {
                resultMark = car.getMark();
                most = newMost;
            }
        }

        while (!tempCars.isEmpty()) {
            cars.push(tempCars.pop());
        }

        return  resultMark;
    }

    /**
     * Busca o modelo mais popular dentre os carros que estão na pilha
     *
     * @return o modelo mais popular encontrado
     */
    @Override
    public String getMostPopularModel() {
        Stackable<Car> tempCars = new LinkedStack<>(20);
        String resultModel = null;
        int most = 0;

        if (!cars.isEmpty()) {
            Car car = cars.pop();
            tempCars.push(car);
            most = countModelOcurrences(car.getModel(), cars);
            resultModel = car.getModel();
        }

        while (!cars.isEmpty()) {
            Car car = cars.pop();
            tempCars.push(car);
            int newMost = countModelOcurrences(car.getModel(), cars);
            if (newMost > most) {
                resultModel = car.getModel();
                most = newMost;
            }
        }

        while (!tempCars.isEmpty()) {
            cars.push(tempCars.pop());
        }

        return  resultModel;
    }

    /**
     *Busca a cor mais popular dentre os carros que estão na pilha
     *
     * @return a cor mais popular encontrada
     */
    @Override
    public String getMostPopularColor() {
        Stackable<Car> tempCars = new LinkedStack<>(20);
        String resultColor = null;
        int most = 0;

        while (!cars.isEmpty()) {
            Car car = cars.pop();
            tempCars.push(car);
            if (car.getColor() != null) {
                most = countColorsOcurrences(car.getColor(), cars);
                resultColor = car.getColor();
                break;
            }
        }


        while (!cars.isEmpty()) {
            Car car = cars.pop();
            tempCars.push(car);
            if (car.getColor() != null) {
                int newMost = countColorsOcurrences(car.getColor(), cars);
                if (newMost > most) {
                    resultColor = car.getColor();
                    most = newMost;
                }
            }
        }

        while (!tempCars.isEmpty()) {
            cars.push(tempCars.pop());
        }

        return  resultColor;
    }

    // Operações de gerenciamento
    /**
     * Verifica se o carro está na pilha
     *
     * @param plateLicense, placa do carro a ser verificado
     * @return true se estiver na pilha, e false caso contrário
     */
    @Override
    public boolean isCarInPlaced(String plateLicense) {
        Stackable<Car> tempCars = new LinkedStack<>(20);
        boolean isPlaced = false;

        while (!cars.isEmpty()) {
            Car car = cars.pop();
            tempCars.push(car);
            if (plateLicense.equalsIgnoreCase(car.getLicensePlate())) {
                isPlaced = true;
                break;
            }
        }

        while (!tempCars.isEmpty()) {
            cars.push(tempCars.pop());
        }

        return isPlaced;
    }

    /**
     * Apaga todos os carros da pilha, zerando a pilha
     */
    @Override
    public void clearAllCars() {
        while (!cars.isEmpty()) {
            cars.pop();
        }
    }

    /**
     * Remove da pilha todos os carros cuja data de chegada seja anterior à data informada.
     *
     * @param date data limite; carros que chegaram antes dela serão removidos
     */
    @Override
    public void removeCarsOlderThan(LocalDateTime date) {
        Stackable<Car> tempCars = new LinkedStack<>(20);

        while (!cars.isEmpty()) {
            Car car = cars.pop();
            if (car.getArrived() != null && car.getArrived().isBefore(date)) {
                continue;
            }
            tempCars.push(car);
        }

        while (!tempCars.isEmpty()) {
            cars.push(tempCars.pop());
        }
    }

    /**
     *Busca carros com duração estacionado dentro de um horário definido
     * 
     * @param minHours hora mínima
     * @param maxHours hora máxima
     * @return carros encontrados
     */
    @Override
    public Car[] getCarsByParkingDuration(long minHours, long maxHours) {
        Stackable<Car> tempCars = new LinkedStack<>(20);
        Stackable<Car> foundCars = new LinkedStack<>(20);

        LocalDateTime now = LocalDateTime.now();

        while (!cars.isEmpty()) {
            Car car = cars.pop();
            tempCars.push(car);
            if (car.getArrived() != null) {
                long parkedDuration = Duration.between(car.getArrived(), now).toHours();
                if (parkedDuration >= minHours && parkedDuration <= maxHours) {
                    foundCars.push(car);
                }
            }
        }

        while (!tempCars.isEmpty()) {
            cars.push(tempCars.pop());
        }
        
        return stackToArray(foundCars);
    }

    /**
     * Verifica a quantidade de espaços ainda disponíveis na pilha de carros
     *
     * @return número de espaços disponíveis
     */
    @Override
    public int getAvailableSpaces() {
        return getMaxCapacity() - countElements(cars);
    }

    /**
     * verifica se o estacionamento está vazio
     *
     * @return true se estiver vazio, e false caso contrário
     */
    @Override
    public boolean isParkingEmpty() {
       return cars.isEmpty();
    }

    /**
     * Retorna a capacidade máxima da pilha de carros
     *
     * @return capacidade máxima da pilha
     */
    @Override
    public int getMaxCapacity() {
        Stackable<Car> aux = new LinkedStack<>(20);
        
        while (!aux.isFull()){
            aux.push(cars.peek());
        }

        int capacity = countElements(aux);
        return capacity;
    }

    /**
     * Retorna a taxa de ocupação do estacionamento em porcentagem.
     *
     * @return int representando a porcentagem de vagas ocupadas (0 a 100)
     */
    @Override
    public int getOccupancyRate() {
        int totalCars = countElements(cars);
        int maxCapacity = getMaxCapacity();

        if (maxCapacity == 0) {
            return 0;
        }

        return (totalCars * 100) / maxCapacity;
    }

    /**
     * verifica se o estacionamento está cheio
     *
     * @return true se estiver cheio, e false caso contrário
     */
    @Override
    public boolean isParkingFull() {
        return cars.isFull();
    }

    /**
     * Retorna a duração que um carro está estacionado em horas, dado sua placa.
     *
     * @param plateLicense placa do carro a ser verificado
     * @return duração em horas que o carro está estacionado
     * @throws java.util.NoSuchElementException se o carro não for encontrado na pilha
     *         ou se o carro não tiver a hora de chegada definida
     */
    @Override
    public long getParkingDuration(String plateLicense) {
        Stackable<Car> tempCars = new LinkedStack<>(20);
        Car foundCar = null;

        while (!cars.isEmpty()) {
            Car car = cars.pop();
            tempCars.push(car);
            if (car.getLicensePlate().equalsIgnoreCase(plateLicense)) {
                foundCar = car;
                break;
            }
        }

        while (!tempCars.isEmpty()) {
            cars.push(tempCars.pop());
        }
        
        if (foundCar == null || foundCar.getArrived() == null) {
            throw new NoSuchElementException("Carro não encontrado ou sem hora de chegada definida");
        }

        LocalDateTime now = LocalDateTime.now();
        return Duration.between(foundCar.getArrived(), now).toHours();

    }

    /**
     * Remove todos os carros de um determinado proprietário da pilha.
     *
     * @param owner nome do proprietário cujos carros serão removidos
     */
    @Override
    public void removeCarsByOwner(String owner) {
        Stackable<Car> tempCars = new LinkedStack<>(20);

        while (!cars.isEmpty()) {
            Car car = cars.pop();
            if (car.getOwnerName() != null && car.getOwnerName().equalsIgnoreCase(owner)) {
                continue;
            }
            tempCars.push(car);
        }

        while (!tempCars.isEmpty()) {
            cars.push(tempCars.pop());
        }
    }

    /**
     * Calcula o tempo médio de chegada dos carros na pilha.
     *
     * @return tempo médio de chegada em minutos
     * @throws java.util.NoSuchElementException se não houver carros com data de chegada definida
     */
    @Override
    public long getAverageArrivalTime() {
        Stackable<Car> tempCars = new LinkedStack<>(20);
        long totalMinutes = 0;
        int count = 0;

        while (!cars.isEmpty()) {
            Car car = cars.pop();
            tempCars.push(car);

            if (car.getArrived() != null) {
                long minutes;
                minutes = car.getArrived().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() / 60000;
                totalMinutes += minutes;
                count++;
            }
        }

        while (!tempCars.isEmpty()) {
            cars.push(tempCars.pop());
        }

        if (count == 0) {
            throw new NoSuchElementException("Não há carros com data de chegada definida");
        }

        return totalMinutes / count;
    }


    /**
     * Busca os carros que estão estacionados há mais de um determinado número de horas.
     *
     * @param thresholdHours número de horas de permanência mínima
     * @return array com os carros encontrados
     */
    @Override
    public Car[] getCarsWithLongParking(long thresholdHours) {
        Stackable<Car> tempCars = new LinkedStack<>(20);
        Stackable<Car> foundCars = new LinkedStack<>(20);
        LocalDateTime now = LocalDateTime.now();

        while (!cars.isEmpty()) {
            Car car = cars.pop();
            tempCars.push(car);

            if (car.getArrived() != null) {
                long parkedHours = Duration.between(car.getArrived(), now).toHours();
                if (parkedHours > thresholdHours) {
                    foundCars.push(car);
                }
            }
        }

        while (!tempCars.isEmpty()) {
            cars.push(tempCars.pop());
        }

        return stackToArray(foundCars);
    }

    /**
     * Converte uma pilha de carros em um array
     *
     * @param stack, pilha a ser convertida
     * @return array com todos os carros da pilhas
     */
    private Car[] stackToArray(Stackable<Car> stack) {
        Car[] arrayCars = new Car[countElements(stack)];
        int index = 0;
        while (!stack.isEmpty()) {
            arrayCars[index] = stack.pop();
            index++;
        }
        return arrayCars;
    }

    /**
     * Conta a quantidade de elementos na pilha
     *
     * @param stack, pilha a ser contada
     * @return o número de elementos na pilha
     */
    private int countElements(Stackable<Car> stack) {
        Stackable<Car> tempCars = new LinkedStack<>(20);
        int count = 0;

        while (!stack.isEmpty()) {
            tempCars.push(stack.pop());
            count++;
        }

        while (!tempCars.isEmpty()) {
            stack.push(tempCars.pop());
        }

        return count;
    }

    /**
     * Conta o número de vezes que uma marca se repete na pilha
     *
     * @param mark, marca a ser contada
     * @param stack, pilha temporária a ser desempilhada para a verificação
     * @return int com a quantidade de vezes que a marca apareceu
     */
    private int countMarkOcurrences(String mark, Stackable<Car> stack) {
        Stackable<Car> aux = new LinkedStack<>(20);
        int count = 0;

        while (!stack.isEmpty()) {
            Car car = stack.pop();
            aux.push(car);
            if (car.getMark().equalsIgnoreCase(mark)) {
                count++;
            }
        }

        while (!aux.isEmpty()) {
            stack.push(aux.pop());
        }

        return count;
    }

    /**
     * Conta o número de vezes que um modelo se repete na pilha
     *
     * @param model, modelo a ser contado
     * @param stack, pilha temporária a ser desempilhada para a verificação
     * @return int com a quantidade de vezes que o modelo apareceu
     */
    private int countModelOcurrences(String model, Stackable<Car> stack) {
        Stackable<Car> aux = new LinkedStack<>(20);
        int count = 0;

        while (!stack.isEmpty()) {
            Car car = stack.pop();
            aux.push(car);
            if (car.getModel().equalsIgnoreCase(model)) {
                count++;
            }
        }

        while (!aux.isEmpty()) {
            stack.push(aux.pop());
        }

        return count;
    }

    /**
     * Conta o número de vezes que uma cor se repete na pilha
     *
     * @param color, cor a ser contada
     * @param stack, pilha temporária a ser desempilhada para a verificação
     * @return int com a quantidade de vezes que a cor apareceu
     */
    private int countColorsOcurrences(String color, Stackable<Car> stack) {
        Stackable<Car> aux = new LinkedStack<>(20);
        int count = 0;

        while (!stack.isEmpty()) {
            Car car = stack.pop();
            aux.push(car);
            if (car.getColor() != null && car.getColor().equalsIgnoreCase(color)) {
                count++;
            }
        }

        while (!aux.isEmpty()) {
            stack.push(aux.pop());
        }

        return count;
    }
}
