package com.ilearning.huaweiod.basic.string;

/**
 * 打印文件
 *
 * @author yuwenbo
 * @date 2023/3/3 6:39
 **/

/**
 * 题目描述：
 * 有5台打印机打印文件，每台打印机有自己的待打印队列。因为打印的文件内容有轻重缓急之分，所以队列中的文件有1~10不同的优先级，其中数字越大优先级越高。打印机会从自己的待打印队列中选择优先级最高的文件来打印。如果存在两个优先级一样的文件，则选择最早进入队列的那个文件。 现在请你来模拟这5台打印机的打印过程。
 *
 */
import java.util.PriorityQueue;

public class PrinterScheduler {

    // 打印任务类，记录文件ID、优先级和进入队列时间
    private static class PrintJob implements Comparable<PrintJob> {
        int id;
        int priority;
        long enqueueTime; // 使用系统时间记录进入队列的时间

        public PrintJob(int id, int priority, long enqueueTime) {
            this.id = id;
            this.priority = priority;
            this.enqueueTime = enqueueTime;
        }

        // 优先级高的先打印，如果优先级相同则先进队列的先打印
        public int compareTo(PrintJob other) {
            if (this.priority != other.priority) {
                return other.priority - this.priority;
            } else {
                return (int) (this.enqueueTime - other.enqueueTime);
            }
        }
    }

    // 打印机类，记录打印机ID和待打印队列
    private static class Printer {
        int id;
        PriorityQueue<PrintJob> queue;

        public Printer(int id) {
            this.id = id;
            this.queue = new PriorityQueue<>();
        }

        // 将打印任务加入待打印队列
        public void addPrintJob(PrintJob job) {
            queue.offer(job);
        }

        // 从待打印队列中取出优先级最高的打印任务
        public PrintJob getTopPrintJob() {
            return queue.poll();
        }
    }

    public static void main(String[] args) {
        // 创建5台打印机
        Printer[] printers = new Printer[5];
        for (int i = 0; i < 5; i++) {
            printers[i] = new Printer(i + 1);
        }

        // 生成10个打印任务，优先级从1到10，随机分配到5台打印机的待打印队列中
        for (int i = 1; i <= 10; i++) {
            int priority = i;
            int printerId = (int) (Math.random() * 5); // 随机分配打印机
            long enqueueTime = System.currentTimeMillis(); // 使用系统时间记录进入队列的时间
            PrintJob job = new PrintJob(i, priority, enqueueTime);
            printers[printerId].addPrintJob(job);
        }

        // 循环打印，每次从5台打印机的待打印队列中取出优先级最高的打印任务进行打印
        while (true) {
            boolean allEmpty = true;
            for (Printer printer : printers) {
                PrintJob job = printer.getTopPrintJob();
                if (job != null) {
                    allEmpty = false;
                    System.out.println("Printer " + printer.id + " is printing job " + job.id + " (priority " + job.priority + ")");
                }
            }
            if (allEmpty) {
                break; // 所有打印机的待打印队列都为空，退出循环
            }
        }

    }
}
