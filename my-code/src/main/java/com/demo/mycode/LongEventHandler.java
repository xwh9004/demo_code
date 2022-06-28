package com.demo.mycode;

import com.lmax.disruptor.EventHandler;

public class LongEventHandler implements EventHandler<LongEvent>

    {
        @Override
        public void onEvent (LongEvent event,long sequence, boolean endOfBatch)
        {
            System.out.println("Event: " + event);
        }
    }