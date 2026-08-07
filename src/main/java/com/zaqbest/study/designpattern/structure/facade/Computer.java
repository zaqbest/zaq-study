package com.zaqbest.study.designpattern.structure.facade;

public class Computer {
    CPU cpu;
    Memory memory;

    public Computer(CPU cpu, Memory memory) {
        this.cpu = cpu;
        this.memory = memory;
    }

    public void startComputer(){
        memory.load(0);
        cpu.jumpTo(0);
        cpu.execute();
    }
}

class CPU{
    public void execute(){}
    public void jumpTo(int position){}
}

class Memory{
    public void load(int positon){}
}
