package br.edu.ifba.vca.bsi.ed.linkeddequedao.dao;

import br.edu.ifba.vca.bsi.ed.linkeddequedao.dao.repository.DEQueable;
import br.edu.ifba.vca.bsi.ed.linkeddequedao.dao.repository.LinkedDEQue;
import br.edu.ifba.vca.bsi.ed.linkeddequedao.model.Car;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.NoSuchElementException;


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

    /**
     * Busca um carro na fila pela sua placa
     *
     * @param licensePlate, placa do carro a ser buscado
     * @return carro encontrado
     */
    @Override
    public Car getCarByLicensePlate(String licensePlate) {
        DEQueable<Car> tempCars = new LinkedDEQue<>(20);
        Car resultCar = null;

        while (!cars.isEmpty()) {
            Car car = cars.beginDequeue();
            tempCars.endEnqueue(car);
            if (car.getLicensePlate().equalsIgnoreCase(licensePlate)) {
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
     * Busca todos os carros na fila por uma mesma marca
     *
     * @param mark, marca a ser buscada
     * @return array com os carros encontrados
     */
    @Override
    public Car[] getCarsByMark(String mark) {
        DEQueable<Car> tempCars = new LinkedDEQue<>(20);
        DEQueable<Car> foundCars = new LinkedDEQue<>(20);

        while (!cars.isEmpty()) {
            Car car = cars.beginDequeue();
            tempCars.endEnqueue(car);
            if (car.getMark().equalsIgnoreCase(mark)) {
                foundCars.endEnqueue(car);
            }
        }

        while (!tempCars.isEmpty()) {
            cars.endEnqueue(tempCars.beginDequeue());
        }

        return dequeToArray(foundCars);
    }

    /**
     * Busca todos os carros na fila por um mesmo modelo
     *
     * @param model, modelo a ser buscado
     * @return array com os carros encontrados
     */
    @Override
    public Car[] getCarsByModel(String model) {
        DEQueable<Car> tempCars = new LinkedDEQue<>(20);
        DEQueable<Car> foundCars = new LinkedDEQue<>(20);

        while (!cars.isEmpty()) {
            Car car = cars.beginDequeue();
            tempCars.endEnqueue(car);
            if (car.getModel().equalsIgnoreCase(model)) {
                foundCars.endEnqueue(car);
            }
        }

        while (!tempCars.isEmpty()) {
            cars.endEnqueue(tempCars.beginDequeue());
        }

        return dequeToArray(foundCars);
    }

    /**
     * Busca todos os carros na fila por uma mesma cor
     *
     * @param color, cor a ser buscada
     * @return array com os carros encontrados
     */
    @Override
    public Car[] getCarsByColor(String color) {
        DEQueable<Car> tempCars = new LinkedDEQue<>(20);
        DEQueable<Car> foundCars = new LinkedDEQue<>(20);

        while (!cars.isEmpty()) {
            Car car = cars.beginDequeue();
            tempCars.endEnqueue(car);
            if (car.getColor() != null && car.getColor().equalsIgnoreCase(color)) {
                foundCars.endEnqueue(car);
            }
        }

        while (!tempCars.isEmpty()) {
            cars.endEnqueue(tempCars.beginDequeue());
        }

        return dequeToArray(foundCars);
    }

    /**
     * Busca todos os carros na fila por um mesmo proprietário
     *
     * @param owner, nome do proprietário a ser buscado
     * @return array com os carros encontrados
     */
    @Override
    public Car[] getCarsByOwner(String owner) {
        DEQueable<Car> tempCars = new LinkedDEQue<>(20);
        DEQueable<Car> foundCars = new LinkedDEQue<>(20);

        while (!cars.isEmpty()) {
            Car car = cars.beginDequeue();
            tempCars.endEnqueue(car);
            if (car.getOwnerName() != null && car.getOwnerName().equalsIgnoreCase(owner)) {
                foundCars.endEnqueue(car);
            }
        }

        while (!tempCars.isEmpty()) {
            cars.endEnqueue(tempCars.beginDequeue());
        }

        return dequeToArray(foundCars);    }

    /**
     * Busca todos os carros na fila que estão num intervalo de tempo definido,
     * pela sua data de chegada
     *
     * @param initialMoment, data inicial do intervalo
     * @param finalMoment, data final do intervalo
     * @return array com os carros encontrados
     */
    @Override
    public Car[] getCarsByMomentArrival(LocalDateTime initialMoment, LocalDateTime finalMoment) {
        DEQueable<Car> tempCars = new LinkedDEQue<>(20);
        DEQueable<Car> foundCars = new LinkedDEQue<>(20);

        while (!cars.isEmpty()) {
            Car car = cars.beginDequeue();
            tempCars.endEnqueue(car);
            if (car.getArrived() != null) {
                if (car.getArrived().isAfter(initialMoment) && car.getArrived().isBefore(finalMoment)) {
                    foundCars.endEnqueue(car);
                }
            }
        }

        while (!tempCars.isEmpty()) {
            cars.endEnqueue(tempCars.beginDequeue());
        }

        return dequeToArray(foundCars);
    }

    /**
     * Busca os carro que estão estacionados há mais de um determinado número de horas
     *
     * @param thresholdHours, número de horas de permanênci mínima
     * @return array com os carros encontrados
     */
    @Override
    public Car[] getCarsWithLongParking(long thresholdHours) {
        DEQueable<Car> tempCars = new LinkedDEQue<>(20);
        DEQueable<Car> foundCars = new LinkedDEQue<>(20);
        LocalDateTime now = LocalDateTime.now();

        while (!cars.isEmpty()) {
            Car car = cars.beginDequeue();
            tempCars.endEnqueue(car);
            if (car.getArrived() != null) {
                long parkedHours = Duration.between(car.getArrived(), now).toHours();
                if (parkedHours > thresholdHours) {
                    foundCars.endEnqueue(car);
                }
            }
        }

        while (!tempCars.isEmpty()) {
            cars.endEnqueue(tempCars.beginDequeue());
        }

        return dequeToArray(foundCars);
    }

    /**
     * Calcula o tempo médio de chegada dos na pilha
     *
     * @return tempo médio de chegada em minutos
     * @throws java.util.NoSuchElementException, se não houver carros com data de chegada definida
     */
    @Override
    public long getAverageArrivalTime() {
        DEQueable<Car> tempCars = new LinkedDEQue<>(20);
        long totalMinutes = 0;
        int count = 0;

        while (!cars.isEmpty()) {
            Car car = cars.beginDequeue();
            tempCars.endEnqueue(car);
            if (car.getArrived() != null) {
                long minutes = car.getArrived().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() / 60000;
                totalMinutes += minutes;
                count++;
            }
        }
        while (!tempCars.isEmpty()) {
            cars.endEnqueue(tempCars.beginDequeue());
        }

        if (count == 0) {
            throw new NoSuchElementException("Não há carros com a data de chegada definida");
        }

        return totalMinutes / count;
    }

    /**
     * Busca o carro com a data de chegada mais recente
     *
     * @return carro com a data de chegada mais recente
     * @throws java.util.NoSuchElementException, se não houver carros com data de chegada definda
     */
    @Override
    public Car getCarByNewestArrival() {
        DEQueable<Car> tempCars = new LinkedDEQue<>(20);
        Car resultCar = null;

        while (!cars.isEmpty()) {
            Car car = cars.beginDequeue();
            tempCars.endEnqueue(car);
            if (car.getArrived() != null) {
                resultCar = car;
                break;
            }
        }

        while (!cars.isEmpty()) {
            Car car = cars.beginDequeue();
            tempCars.endEnqueue(car);
            if (car.getArrived() != null) {
                if (car.getArrived().isAfter(resultCar.getArrived())) {
                    resultCar = car;
                }
            }
        }

        while (!tempCars.isEmpty()) {
            cars.endEnqueue(tempCars.beginDequeue());
        }

        if (resultCar == null) {
            throw  new NoSuchElementException("Não há carros com data de chegada definida");
        }

        return resultCar;
    }

    /**
     * Busca o carro com a data de chegada mais antiga
     *
     * @return carro com a data de chegada mais antiga
     * @throws java.util.NoSuchElementException, se não houver carros com data de chegada definda
     */
    @Override
    public Car getCarByOldestArrival() {
        DEQueable<Car> tempCars = new LinkedDEQue<>(20);
        Car resultCar = null;

        while (!cars.isEmpty()) {
            Car car = cars.beginDequeue();
            tempCars.endEnqueue(car);
            if (car.getArrived() != null) {
                resultCar = car;
                break;
            }
        }

        while (!cars.isEmpty()) {
            Car car = cars.beginDequeue();
            tempCars.endEnqueue(car);
            if (car.getArrived() != null) {
                if (car.getArrived().isBefore(resultCar.getArrived())) {
                    resultCar = car;
                }
            }
        }

        while (!tempCars.isEmpty()) {
            cars.endEnqueue(tempCars.beginDequeue());
        }

        if (resultCar == null) {
            throw  new NoSuchElementException("Não há carros com data de chegada definida");
        }

        return resultCar;
    }

    /**
     * Imprime todos os carros que estão na fila
     *
     * @return String representando os carros da fila
     */
    @Override
    public String printCars() {
        return cars.toString();
    }

    /**
     * Retorna a quantidade de carros presentes na fila
     *
     * @return int com o número total
     */
    @Override
    public int getTotalCars() {
        return countElements(cars);
    }

    /**
     * Retorna a marca mais popular da fila
     *
     * @return marca mais popular
     */
    @Override
    public String getMostPopularMark() {
        DEQueable<Car> tempCars = new LinkedDEQue<>(20);
        String resultMark = null;
        int counted = 0;

        if (!cars.isEmpty()) {
            Car car = cars.beginDequeue();
            tempCars.endEnqueue(car);
            resultMark = car.getMark();
            counted = countMarkOcurrences(cars, car.getMark());
        }

        while (!cars.isEmpty()) {
            Car car = cars.beginDequeue();
            tempCars.endEnqueue(car);
            int countMark = countMarkOcurrences(cars, car.getMark());
            if (countMark > counted) {
                resultMark = car.getMark();
                counted = countMark;
            }
        }

        while (!tempCars.isEmpty()) {
            cars.endEnqueue(tempCars.beginDequeue());
        }

        return resultMark;
    }

    /**
     * Retorna o modelo mais popular da fila
     *
     * @return modelo mais popular
     */
    @Override
    public String getMostPopularModel() {
        DEQueable<Car> tempCars = new LinkedDEQue<>(20);
        String resultModel = null;
        int counted = 0;

        if (!cars.isEmpty()) {
            Car car = cars.beginDequeue();
            tempCars.endEnqueue(car);
            resultModel = car.getModel();
            counted = countModelOcurrences(cars, car.getModel());

        }

        while (!cars.isEmpty()) {
            Car car = cars.beginDequeue();
            tempCars.endEnqueue(car);
            int countModel = countModelOcurrences(cars, car.getModel());
            if (countModel > counted) {
                resultModel = car.getModel();
                counted = countModel;
            }
        }

        while (!tempCars.isEmpty()) {
            cars.endEnqueue(tempCars.beginDequeue());
        }

        return resultModel;
    }

    /**
     * Retorna a cor mais popular da fila
     *
     * @return cor mais popular
     * @throws java.util.NoSuchElementException, se não houver carros com cor definida
     */
    @Override
    public String getMostPopularColor() {
        DEQueable<Car> tempCars = new LinkedDEQue<>(20);
        String resultColor = null;
        int counted = 0;

        while (!cars.isEmpty()) {
            Car car = cars.beginDequeue();
            tempCars.endEnqueue(car);
            if (car.getColor() != null) {
                resultColor = car.getColor();
                counted = countColorOcurrences(cars, car.getColor());
                break;
            }

        }

        while (!cars.isEmpty()) {
            Car car = cars.beginDequeue();
            tempCars.endEnqueue(car);
            if (car.getColor() != null) {
                int countColor = countColorOcurrences(cars, car.getColor());
                if (countColor > counted) {
                    resultColor = car.getColor();
                    counted = countColor;
                }
            }
        }

        while (!tempCars.isEmpty()) {
            cars.endEnqueue(tempCars.beginDequeue());
        }

        if (resultColor == null) {
            throw new NoSuchElementException("Não há carros com cor definida");
        }

        return resultColor;
    }

    /**
     * Retorna o tempo que um carro está estacionado em minutos
     *
     * @param plateLicense placa do carro a ser verificado o tempo que está estacionado
     * @return tempo estacionado em minutos
     * @throws java.util.NoSuchElementException, se o carro não for encontrado
     *         ou se não tiver a data de chegada definida
     */
    @Override
    public long getParkingDuration(String plateLicense) {
        DEQueable<Car> tempCars = new LinkedDEQue<>(20);
        LocalDateTime now = LocalDateTime.now();
        Long parkedMinutes = null;

        while (!cars.isEmpty()) {
            Car car = cars.beginDequeue();
            tempCars.endEnqueue(car);
            if (car.getLicensePlate().equalsIgnoreCase(plateLicense) && car.getArrived() != null) {
                parkedMinutes = Duration.between(car.getArrived(), now).toMinutes();
                break;
            }
        }

        while (!tempCars.isEmpty()) {
            cars.beginEnqueue(tempCars.endDequeue());
        }

        if (parkedMinutes == null) {
            throw new NoSuchElementException("Carro não encontrado ou com data de chegada não definida");
        }

        return parkedMinutes;
    }

    /**
     * Retorna todos os carros que tem a sua duração estacionada dentro de um intervalo de tempo delimitado
     *
     * @param minHours hora mínima do intevalo
     * @param maxHours hora máxima do intervalo
     * @return array com carros encontrados
     */
    @Override
    public Car[] getCarsByParkingDuration(long minHours, long maxHours) {
        DEQueable<Car> tempCars = new LinkedDEQue<>(20);
        DEQueable<Car> foundCars = new LinkedDEQue<>(20);
        LocalDateTime now = LocalDateTime.now();

        while (!cars.isEmpty()) {
            Car car = cars.beginDequeue();
            tempCars.endEnqueue(car);
            if (car.getArrived() != null) {
                long duration = Duration.between(car.getArrived(), now).toHours();
                if (duration >= minHours && duration <= maxHours) {
                    foundCars.endEnqueue(car);
                }
            }
        }

        while (!tempCars.isEmpty()) {
            cars.endEnqueue(tempCars.beginDequeue());
        }

        return dequeToArray(foundCars);
    }

    /**
     * Verifica se um carro está no estacionamento
     *
     * @param plateLicense placa do carro a ser verificado
     * @return true se o carro estiver no estacionamento, false caso contrário
     */
    @Override
    public boolean isCarInPlaced(String plateLicense) {
        DEQueable<Car> tempCars = new LinkedDEQue<>(20);
        boolean placed = false;

        while (!cars.isEmpty()) {
            Car car = cars.beginDequeue();
            tempCars.endEnqueue(car);
            if (car.getLicensePlate().equalsIgnoreCase(plateLicense)) {
                placed = true;
            }
        }

        while (!tempCars.isEmpty()) {
            cars.endEnqueue(tempCars.beginDequeue());
        }

        return placed;
    }

    /**
     * Restaura a fila de carros, removendo todos os seus elementos
     */
    @Override
    public void clearAllCars() {
        while (!cars.isEmpty()) {
            cars.beginDequeue();
        }
    }

    /**
     * Calcula a quantidade de espaços disponíveis
     *
     * @return o número de vagas
     */
    @Override
    public int getAvailableSpaces() {
        return getMaxCapacity() - countElements(cars);
    }

    /**
     * Retorna a taxa de ocupação do estacionamento em porcentagem
     *
     * @return int respresentando a porcentagem de vagas ocupadas
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
     * Verifica se a fila de carros está cheia
     *
     * @return true se estiver cheia, false caso contrário
     */
    @Override
    public boolean isParkingFull() {
        return cars.isFull();
    }

    /**
     * Verifica se a fila de carros está vazia
     *
     * @return true se estiver vazia, false caso contrário
     */
    @Override
    public boolean isParkingEmpty() {
        return cars.isEmpty();
    }

    /**
     * Conta a capacidade máxima da fila de carros
     *
     * @return capacidade máxima
     */
    @Override
    public int getMaxCapacity() {
        DEQueable<Car> tempCars = new LinkedDEQue<>(countElements(cars));
        int maxCapacity = 0;

        while (!cars.isEmpty()) {
            tempCars.endEnqueue(cars.beginDequeue());
        }

        while (!cars.isFull()) {
            cars.endEnqueue(new Car("XXX0000", "xx", "xx"));
            maxCapacity++;
        }


        while (!cars.isEmpty()) {
            cars.beginDequeue();
        }

        while (!tempCars.isEmpty()) {
            cars.endEnqueue(tempCars.beginDequeue());
        }

        return maxCapacity;
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

    /**
     * Conta o número de vezes que uma marca aparece na fila
     *
     * @param aux, fila auxiliar a ser percorrida
     * @param mark, marca a ser contada na fila
     * @return o número de vezes que a marca apareceu
     */
    private int countMarkOcurrences(DEQueable<Car> aux, String mark) {
        DEQueable<Car> tempAux = new LinkedDEQue<>(20);
        int count = 0;

        while (!aux.isEmpty()) {
            Car car = aux.beginDequeue();
            tempAux.endEnqueue(car);
            if (car.getMark().equalsIgnoreCase(mark)) {
                count++;
            }
        }

        while (!tempAux.isEmpty()) {
            aux.endEnqueue(tempAux.beginDequeue());
        }

        return count;
    }

    /**
     * Conta o número de vezes que um modelo aparece na fila
     *
     * @param aux, fila auxiliar a ser percorrida
     * @param model, modelo a ser contado na fila
     * @return o número de vezes que o modelo apareceu
     */
    private int countModelOcurrences(DEQueable<Car> aux, String model) {
        DEQueable<Car> tempAux = new LinkedDEQue<>(20);
        int count = 0;

        while (!aux.isEmpty()) {
            Car car = aux.beginDequeue();
            tempAux.endEnqueue(car);
            if (car.getModel() != null && car.getModel().equalsIgnoreCase(model)) {
                count++;
            }
        }

        while (!tempAux.isEmpty()) {
            aux.endEnqueue(tempAux.beginDequeue());
        }

        return count;
    }

    /**
     * Conta o número de vezes que uma cor aparece na fila
     *
     * @param aux, fila auxiliar a ser percorrida
     * @param color, cor a ser contada na fila
     * @return o número de vezes que a cor apareceu
     */
    private int countColorOcurrences(DEQueable<Car> aux, String color) {
        DEQueable<Car> tempAux = new LinkedDEQue<>(20);
        int count = 0;

        while (!aux.isEmpty()) {
            Car car = aux.beginDequeue();
            tempAux.endEnqueue(car);
            if (car.getColor() != null && car.getColor().equalsIgnoreCase(color)) {
                count++;
            }
        }

        while (!tempAux.isEmpty()) {
            aux.endEnqueue(tempAux.beginDequeue());
        }

        return count;
    }
}
