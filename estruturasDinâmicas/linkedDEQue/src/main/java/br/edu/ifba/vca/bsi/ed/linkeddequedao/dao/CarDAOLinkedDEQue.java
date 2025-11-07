package br.edu.ifba.vca.bsi.ed.linkeddequedao.dao;

import br.edu.ifba.vca.bsi.ed.linkeddequedao.dao.repository.DEQueable;
import br.edu.ifba.vca.bsi.ed.linkeddequedao.dao.repository.LinkedDEQue;
import br.edu.ifba.vca.bsi.ed.linkeddequedao.model.Car;

import java.time.LocalDateTime;


/**
 * Implemantação de um gerenciamento de carros utilizando uma estrutura de dados do
 * tipo Fila com Dupla Terminação Dinâmica
 *
 * @author Matheus Pereira Rodrigues
 * @version  1.0
 * @since 07-11-2025
 * @see CarDAO
 * @see Car
 * @see DEQueable
 * @see LinkedDEQue
 */
public class CarDAOLinkedDEQue implements CarDAO{
    /**Fila com Dupla Terminação Dinâmica primcipal que armazenrá os carros,
     * com capacidade inicial de 20*/
    DEQueable<Car> cars = new LinkedDEQue<>(20);

    /**
     * Adiciona um carro no fim da fila
     *
     * @param car, carro a ser adicionado
     */
    @Override
    public void addCar(Car car) {
        cars.endEnqueue(car);
    }

    /**
     * Busca um carro na fila pela sua placa
     *
     * @param plateLicense, placa do carro a ser buscado
     * @return carro encontrado
     */
    @Override
    public Car getCar(String plateLicense) {
        DEQueable<Car> tempCars = new LinkedDEQue<>(20);
        Car resultCar = null;

        while (!cars.isEmpty()) {
            Car car = cars.beginDequeue();
            tempCars.endEnqueue(car);
            if (car.getLicensePlate().equalsIgnoreCase(plateLicense)) {
                resultCar = car;
                break;
            }
        }

        while (!tempCars.isEmpty()) {
            cars.beginEnqueue(tempCars.endDequeue());
        }

        return resultCar;
    }

    /**
     *Busca todos os carros da fila
     *
     * @return array com todos os carros
     */
    @Override
    public Car[] getAllCars() {
        return dequeToArray(cars);
    }

    /**
     * Atualiza um carro da fila
     * 
     * @param newCar, carro a ser atualizado
     */
    @Override
    public void updateCar(Car newCar) {
        DEQueable<Car> tempCars = new LinkedDEQue<>(20);
        
        while (!cars.isEmpty()) {
            Car car = cars.beginDequeue();
            if (car.getLicensePlate().equalsIgnoreCase(newCar.getLicensePlate())) {
                car = newCar;
            }
            tempCars.endEnqueue(car);
        }

        while (!tempCars.isEmpty()) {
            cars.endEnqueue(tempCars.beginDequeue());
        }
    }

    /**
     * Remove um carro da fila pela sua placa
     * 
     * @param plateLicense, placa do carro a ser removido
     * @return carro removido
     */
    @Override
    public Car deleteCar(String plateLicense) {
        DEQueable<Car> tempCars = new LinkedDEQue<>(20);
        Car deletedCar = null;

        while (!cars.isEmpty()) {
            Car car = cars.beginDequeue();
            if (car.getLicensePlate().equalsIgnoreCase(plateLicense)) {
                deletedCar = car;
                break;
            }
            tempCars.endEnqueue(car);
        }

        while (!tempCars.isEmpty()) {
            cars.beginEnqueue(tempCars.endDequeue());
        }

        return deletedCar;
    }

    /**
     * Remove todos os carros da fila cujo proprietário tenha o nome informado.
     *
     * @param owner, nome do proprietário cujos carros devem ser removidos
     */
    @Override
    public void removeCarsByOwner(String owner) {
        DEQueable<Car> tempCars = new LinkedDEQue<>(20);

        while (!cars.isEmpty()) {
            Car car = cars.beginDequeue();
            if (car.getOwnerName() != null && car.getOwnerName().equalsIgnoreCase(owner)) {
                continue;
            }
            tempCars.endEnqueue(car);
        }

        while (!tempCars.isEmpty()) {
            cars.endEnqueue(tempCars.beginDequeue());
        }
    }

    /**
     * Remove da fila todos os carros cuja data de chegada seja anterior à data informada
     *
     * @param date, data mínima de chegada permitida
     */
    @Override
    public void removeCarsOlderThan(LocalDateTime date) {
        DEQueable<Car> tempCars = new LinkedDEQue<>(20);

        while (!cars.isEmpty()) {
            Car car = cars.beginDequeue();
            if (car.getArrived() != null && car.getArrived().isBefore(date)) {
                continue;
            }
            tempCars.endEnqueue(car);
        }

        while (!tempCars.isEmpty()) {
            cars.endEnqueue(tempCars.beginDequeue());
        }
    }

    @Override
    public Car getCarByLicensePlate(String licensePlate) {
        return null;
    }

    @Override
    public Car[] getCarsByMark(String mark) {
        return new Car[0];
    }

    @Override
    public Car[] getCarsByModel(String model) {
        return new Car[0];
    }

    @Override
    public Car[] getCarsByColor(String color) {
        return new Car[0];
    }

    @Override
    public Car[] getCarsByOwner(String owner) {
        return new Car[0];
    }

    @Override
    public Car[] getCarsByMomentArrival(LocalDateTime initialMoment, LocalDateTime finalMoment) {
        return new Car[0];
    }

    @Override
    public Car[] getCarsWithLongParking(long thresholdHours) {
        return new Car[0];
    }

    @Override
    public long getAverageArrivalTime() {
        return 0;
    }

    @Override
    public Car getCarByNewestArrival() {
        return null;
    }

    @Override
    public Car getCarByOldestArrival() {
        return null;
    }

    @Override
    public String printCars() {
        return "";
    }

    @Override
    public int getTotalCars() {
        return 0;
    }

    @Override
    public String getMostPopularMark() {
        return "";
    }

    @Override
    public String getMostPopularModel() {
        return "";
    }

    @Override
    public String getMostPopularColor() {
        return "";
    }

    @Override
    public long getParkingDuration(String plateLicense) {
        return 0;
    }

    @Override
    public Car[] getCarsByParkingDuration(long minHours, long maxHours) {
        return new Car[0];
    }

    @Override
    public boolean isCarInPlaced(String plateLicense) {
        return false;
    }

    @Override
    public void clearAllCars() {

    }

    @Override
    public int getAvailableSpaces() {
        return 0;
    }

    @Override
    public int getOccupancyRate() {
        return 0;
    }

    @Override
    public boolean isParkingFull() {
        return false;
    }

    @Override
    public boolean isParkingEmpty() {
        return false;
    }

    @Override
    public int getMaxCapacity() {
        return 0;
    }

    /**
     * Converte uma fila de carros em um array
     *
     * @param deque, fila a ser convertida
     * @return array com todos os carros da fila
     */
    private Car[] dequeToArray(DEQueable<Car> deque) {
        Car[] arrayCars = new Car[countElements(deque)];
        int index = 0;
        while (!deque.isEmpty()) {
            arrayCars[index] = deque.beginDequeue();
            index++;
        }
        return arrayCars;
    }

    /**
     * Conta a quantidade de elementos na fila
     *
     * @param deque, fila a ser contada
     * @return o número de elementos na fila
     */
    private int countElements(DEQueable<Car> deque) {
        DEQueable<Car> tempCars = new LinkedDEQue<>(20);
        int count = 0;

        while (!deque.isEmpty()) {
            tempCars.endEnqueue(deque.beginDequeue());
            count++;
        }

        while (!tempCars.isEmpty()) {
            deque.endEnqueue(tempCars.beginDequeue());
        }

        return count;
    }
}
