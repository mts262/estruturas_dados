package br.edu.ifba.vca.bsi.ed.linkedstackdao.dao;

import br.edu.ifba.vca.bsi.ed.linkedstackdao.dao.repository.Stackable;
import br.edu.ifba.vca.bsi.ed.linkedstackdao.dao.repository.LinkedStack;
import br.edu.ifba.vca.bsi.ed.linkedstackdao.model.Car;
import java.time.LocalDateTime;

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

    @Override
    public String getMostPopularMark() {
        throw new UnsupportedOperationException("Operação ainda não implementada");
    }

    @Override
    public String getMostPopularModel() {
        throw new UnsupportedOperationException("Operação ainda não implementada");
    }

    @Override
    public String getMostPopularColor() {
        throw new UnsupportedOperationException("Operação ainda não implementada");
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

    @Override
    public void removeCarsOlderThan(LocalDateTime date) {
        throw new UnsupportedOperationException("Operação ainda não implementada");
    }

    @Override
    public Car[] getCarsByParkingDuration(long minHours, long maxHours) {
        throw new UnsupportedOperationException("Operação ainda não implementada");
    }

    @Override
    public int getAvailableSpaces() {
        throw new UnsupportedOperationException("Operação ainda não implementada");
    }

    @Override
    public boolean isParkingEmpty() {
        throw new UnsupportedOperationException("Operação ainda não implementada");
    }

    @Override
    public int getMaxCapacity() {
        throw new UnsupportedOperationException("Operação ainda não implementada");
    }

    @Override
    public int getOccupancyRate() {
        throw new UnsupportedOperationException("Operação ainda não implementada");
    }

    @Override
    public boolean isParkingFull() {
        throw new UnsupportedOperationException("Operação ainda não implementada");
    }

    @Override
    public long getParkingDuration(String plateLicense) {
        throw new UnsupportedOperationException("Operação ainda não implementada");
    }

    @Override
    public void removeCarsByOwner(String owner) {
        throw new UnsupportedOperationException("Operação ainda não implementada");
    }

    @Override
    public long getAverageArrivalTime() {
        throw new UnsupportedOperationException("Operação ainda não implementada");
    }

    @Override
    public Car[] getCarsWithLongParking(long thresholdHours) {
        throw new UnsupportedOperationException("Operação ainda não implementada");
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
            tempCars.push(cars.pop());
            count++;
        }

        while (!tempCars.isEmpty()) {
            stack.push(tempCars.pop());
        }

        return count;
    }
}
